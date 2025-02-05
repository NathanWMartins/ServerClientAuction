package model;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import org.json.JSONException;
import views.MainAuction;
import org.json.JSONObject;

/**
 *
 * @author Nathan
 */
public class MulticastClient {

    private final String MULTICAST_ADDRESS;
    private final int MULTICAST_PORT;
    private boolean running = true;
    private final InetAddress groupAddress;
    private final MulticastSocket multicastSocket;
    private final CryptoUtils crypto = new CryptoUtils();
    private int contMessage = 0;
    private boolean newClient = false;
    private SecretKey symmetricKey = null;

    public MulticastClient(String address, int port, SecretKey symmetric) throws IOException {
        this.MULTICAST_ADDRESS = address;
        this.MULTICAST_PORT = port;
        this.symmetricKey = symmetric;
        groupAddress = InetAddress.getByName(MULTICAST_ADDRESS);
        multicastSocket = new MulticastSocket(MULTICAST_PORT);
    }

    public void startListening(MainAuction mainAuctionPanel) {
        try {
            multicastSocket.joinGroup(groupAddress);
            System.out.println("Client connected, " + MULTICAST_ADDRESS + ":" + MULTICAST_PORT);

            byte[] buffer = new byte[1024];
            while (running) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                multicastSocket.receive(packet);
                contMessage++;

                String message = new String(packet.getData(), 0, packet.getLength());
                try {
                    JSONObject jsonMessage = new JSONObject(message);
                    String type = jsonMessage.getString("type");

                    if (type.equals("TIME_LEFT")) {
                        int num = Integer.parseInt(crypto.decryptMessageAES(jsonMessage.getString("time"), symmetricKey));
                        if (contMessage == 1 && num < 15) {
                            newClient = true;
                        } else {
                            newClient = false;
                        }
                    }

                    if (!newClient) {
                        if (!type.equals("BID")) {
                            System.out.println("Message received: " + message);

                            switch (type) {
                                case "START_AUCTION" ->
                                    mainAuctionPanel.startAuctionInfo(message, "START_AUCTION");
                                case "NEW_ITEM" ->
                                    mainAuctionPanel.newItemInfo(jsonMessage);
                                case "NEW_WINNER" ->
                                    mainAuctionPanel.startAuctionInfo(message, "NEW_WINNER");
                                case "TIME_LEFT" ->
                                    mainAuctionPanel.updateTime(message);
                                case "ITEM_FINISHED" ->
                                    mainAuctionPanel.itemFinished(jsonMessage);
                                case "AUCTION_FINISHED" ->
                                    mainAuctionPanel.finishedAuction(message);
                                default ->
                                    System.out.println("Unknown message type: " + message);
                            }
                        }
                    } else {
                        mainAuctionPanel.informClientWaitItem(message);
                    }

                } catch (IOException | JSONException e) {
                    System.err.println("Error processing JSON message: " + e.getMessage());
                } catch (Exception ex) {
                    Logger.getLogger(MulticastClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (IOException e) {
            System.err.println("Error in the client multicast: " + e.getMessage());
        }
    }

    public void sendBid(String cpf, int value, String key) throws Exception {
        try {
            // Criando o JSON com os dados do lance
            JSONObject jsonMessage = new JSONObject();
            jsonMessage.put("type", "BID");
            jsonMessage.put("cpf", cpf);
            jsonMessage.put("value", value);

            // Convertendo para string e criptografando
            String encryptedMessage = crypto.encryptMessageAES(jsonMessage.toString(), crypto.convertBase64ToSecretKey(key));
            byte[] buffer = encryptedMessage.getBytes();

            // Criando e enviando o pacote UDP
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, groupAddress, MULTICAST_PORT);
            multicastSocket.send(packet);

            System.out.println("Bid sent: " + jsonMessage.toString());

        } catch (IOException e) {
            System.err.println("Error sending bid: " + e.getMessage());
        }
    }

    public void stopListening() throws IOException {
        running = false;
        if (!multicastSocket.isClosed()) {
            try (multicastSocket) {
                multicastSocket.leaveGroup(groupAddress);
            }
            System.out.println("Multicast connection closed.");
        }
    }
}
