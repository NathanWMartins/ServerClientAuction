package model;

import java.io.IOException;
import java.net.*;
import views.MainAuction;

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

    public MulticastClient(String address, int port) throws IOException {
        this.MULTICAST_ADDRESS = address;
        this.MULTICAST_PORT = port;
        groupAddress = InetAddress.getByName(MULTICAST_ADDRESS);
        multicastSocket = new MulticastSocket(MULTICAST_PORT);
    }

    public void startListening(MainAuction mainAuctionPanel) {
        try {
            multicastSocket.joinGroup(groupAddress);

            System.out.println("Client conected, " + MULTICAST_ADDRESS + ":" + MULTICAST_PORT);

            byte[] buffer = new byte[1024];
            while (running) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                multicastSocket.receive(packet);
                contMessage++;
                String message = new String(packet.getData(), 0, packet.getLength());
                String[] parts = message.split(":");
                if (parts[0].equals("TIME_LEFT")) {
                    int num = Integer.parseInt(parts[1]);
                    if (contMessage == 1 && num < 30) {
                        newClient = true;
                    }else{
                        newClient = false;
                    }
                }
                if (!newClient) {
                    if (!parts[0].equals("BID")) {
                        System.out.println("Message received: " + message);

                        switch (parts[0]) {
                            case "START_AUCTION" ->
                                mainAuctionPanel.startAuctionInfo(message, "START_AUCTION");
                            case "NEW_ITEM" ->
                                mainAuctionPanel.newItemInfo(message);
                            case "NEW_WINNER" ->
                                mainAuctionPanel.startAuctionInfo(message, "NEW_WINNER");
                            case "TIME_LEFT" ->
                                mainAuctionPanel.updateTime(message);
                            case "ITEM_FINISHED" ->
                                mainAuctionPanel.itemFinished(message);
                            case "AUCTION_FINISHED" ->
                                mainAuctionPanel.finishedAuction(message);
                            default ->
                                System.out.println("Message unknown: " + message);
                        }
                    }
                }else{
                    mainAuctionPanel.informClientWaitItem(message);
                }

            }

        } catch (IOException e) {
            System.err.println("Error in the client multicast: " + e.getMessage());
        }
    }

    public void sendBid(String cpf, int value, String key) throws Exception {
        try {
            String message = "BID: CPF:" + cpf + ",VALUE:" + value;
            String encryptedMessage = crypto.encryptMessageAES(message, crypto.convertBase64ToSecretKey(key));
            byte[] buffer = encryptedMessage.getBytes();

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, groupAddress, MULTICAST_PORT);

            multicastSocket.send(packet);
            System.out.println("Bid sended: " + message);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
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
