package com.mycompany.serverauction;

import java.net.*;
import java.io.*;
import java.security.*;
import java.util.concurrent.*;
import java.util.*;
import java.util.logging.*;
import org.json.*;
import java.util.concurrent.atomic.AtomicInteger;
import javax.crypto.SecretKey;

/**
 *
 * @author Nathan
 */
public final class ServerAuction {

    private static final String MULTICAST_ADDRESS = "239.255.255.1";
    private static String finalMessage = "AUCTION_FINISHED: ";
    private static final int MULTICAST_PORT = 5000, TCP_PORT = 7000;
    private MulticastSocket multicastSocket;
    private InetAddress groupAddress;
    private ExecutorService executor = Executors.newCachedThreadPool();
    private volatile boolean auctionRunning = true;
    private int connectedClients = 0, itemsAuctioned = 0, currentHighestBid = 0;
    private AuctionItem currentItem;
    private String currentHighestBidder = "N/A";
    private List<AuctionItem> auctionItems;
    private List<String> clients;
    private KeyRegistry keyRegistry;
    private SecretKey symmetricKey;
    private CryptoUtils cryptoUtils;

    public ServerAuction() throws NoSuchAlgorithmException {
        try {
            this.keyRegistry = new KeyRegistry();
            this.cryptoUtils = new CryptoUtils();
            this.symmetricKey = cryptoUtils.generateSymmetricKey();
            createLists();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ServerAuction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createLists() {
        /*auctionItems = Arrays.asList(
                new AuctionItem("Notebook Dell Inspiron 15", 1000, 100),
                new AuctionItem("Smartphone Samsung Galaxy S23", 800, 50),
                new AuctionItem("Smart TV LG 55'' 4K", 1500, 200),
                new AuctionItem("Smartwatch Apple Watch Series 8", 700, 50),
                new AuctionItem("PlayStation 5", 2000, 300)
        );*/
        auctionItems = Arrays.asList(
                new AuctionItem("Notebook Dell Inspiron 15", 1000, 100),
                new AuctionItem("Smartphone Samsung Galaxy S23", 800, 50)
        );
        clients = Arrays.asList(
                "11111111111",
                "22222222222",
                "33333333333"
        );
    }

    public void verifyClient() throws Exception {
        ServerSocket serverSocket = new ServerSocket(TCP_PORT);
        try {
            System.out.println("Waiting for connections");
            while (true) {
                // Aguarda conexão do cliente
                Socket clientSocket;
                clientSocket = serverSocket.accept();
                System.out.println("Client in!: " + clientSocket.getInetAddress());

                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                String receivedMessage = reader.readLine();
                System.out.println("Message received: " + receivedMessage);

                try {
                    JSONObject json = new JSONObject(receivedMessage);

                    String cpf = json.getString("cpf");
                    String message = json.getString("message");
                    String signatureBase64 = json.getString("signature");
                    
                    boolean validCPF = true;
                    if (!clients.contains(cpf)) {
                        validCPF = false;
                        System.out.println("CPF is not registered ");
                        writer.println(new JSONObject().put("status", "ERROR").toString());
                    }

                    PublicKey clientPublicKey = keyRegistry.loadPublicKey(cpf);

                    if (keyRegistry.isRegistered(cpf) && validCPF) {
                        byte[] signatureBytes = Base64.getDecoder().decode(signatureBase64);
                        boolean signature = cryptoUtils.verifyClientSignature(message, signatureBytes, clientPublicKey);
                        if (signature) {
                            System.out.println("Valid signature. Client authorized!");
                            String keyBase64 = cryptoUtils.convertSymmetricKeyToBase64(symmetricKey);

                            JSONObject response = new JSONObject();
                            response.put("status", "AUTHORIZED");
                            response.put("multicast_address", cryptoUtils.encryptWithRSA(MULTICAST_ADDRESS, clientPublicKey));
                            response.put("multicast_port", cryptoUtils.encryptWithRSA(String.valueOf(MULTICAST_PORT), clientPublicKey));
                            response.put("symmetric_key", cryptoUtils.encryptWithRSA(keyBase64, clientPublicKey));

                            String jsonString = response.toString();

                            synchronized (this) {
                                connectedClients++;
                                System.out.println("Clients on: " + connectedClients);
                                this.notify();
                            }

                            System.out.println("Sending auction data...");
                            writer.println(jsonString);
                        } else {
                            System.out.println("Invalid signature.");
                            writer.println(new JSONObject().put("status", "UNAUTHORIZED").toString());
                        }
                    } else {
                        System.out.println("CPF not registered.");
                        writer.println(new JSONObject().put("status", "UNAUTHORIZED").toString());
                    }
                } catch (Exception e) {
                    System.out.println("Error processing JSON message: " + e.getMessage());
                    writer.println(new JSONObject().put("status", "ERROR").toString());
                }

                clientSocket.close();
            }
        } catch (IOException | JSONException e) {
            Logger.getLogger(ServerAuction.class
                    .getName()).log(Level.SEVERE, null, e);
        }
    }

    public void startAuction() throws InterruptedException {
        try {
            multicastSocket = new MulticastSocket(MULTICAST_PORT);
            groupAddress = InetAddress.getByName(MULTICAST_ADDRESS);
            multicastSocket.joinGroup(groupAddress);

            synchronized (this) {
                while (connectedClients < 1) {
                    System.out.println("Waiting for at least two connections...");
                    this.wait();
                }
            }

            System.out.println("Starting auction, enough connections detected");

            for (AuctionItem item : auctionItems) {
                itemsAuctioned++;
                currentItem = item;
                currentHighestBid = item.getMinimumBid() - 1;
                currentHighestBidder = "N/A";

                Thread.sleep(3000);

                broadcastAuctionInfo("");

                auctionRunning = true;
                handleBids();

                ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                final AtomicInteger timeLeft = new AtomicInteger(30);

                Runnable auctionTask = () -> {
                    if (timeLeft.get() > 0) {
                        sendTime(timeLeft.get());
                        timeLeft.decrementAndGet();
                    } else {
                        auctionRunning = false;
                        try {
                            announceWinner();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ServerAuction.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        scheduler.shutdown();
                    }
                };

                scheduler.scheduleAtFixedRate(auctionTask, 0, 1, TimeUnit.SECONDS);

                while (auctionRunning) {
                    Thread.sleep(3000);
                }

            }
            closeAuction();

        } catch (IOException ex) {
            Logger.getLogger(ServerAuction.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void broadcastAuctionInfo(String info) throws InterruptedException {
        executor.submit(() -> {
            try {
                String message = "";
                if (info.equals("newWinner")) {
                    message = "NEW_WINNER:"
                            + ",Item em leilão: " + currentItem.getName()
                            + ",Valor mínimo entre lances: " + currentItem.getMinimumBetweenBids()
                            + ",Lance mínimo: R$" + currentItem.getMinimumBid()
                            + ",Maior lance atual: R$" + currentHighestBid
                            + ",Líder: " + currentHighestBidder;
                } else {
                    if (itemsAuctioned == 1) {
                        message = "START_AUCTION:"
                                + ",Item em leilão: " + currentItem.getName()
                                + ",Valor mínimo entre lances: " + currentItem.getMinimumBetweenBids()
                                + ",Lance mínimo: R$" + currentItem.getMinimumBid()
                                + ",Maior lance atual: R$" + currentHighestBid
                                + ",Líder: " + currentHighestBidder;
                    } else {
                        message = "NEW_ITEM:"
                                + ",Item em leilão: " + currentItem.getName()
                                + ",Valor mínimo entre lances: " + currentItem.getMinimumBetweenBids()
                                + ",Lance mínimo: R$" + currentItem.getMinimumBid()
                                + ",Maior lance atual: R$" + currentHighestBid
                                + ",Líder: " + currentHighestBidder;
                    }
                }

                byte[] buffer = message.getBytes();
                DatagramPacket pkt = new DatagramPacket(buffer, buffer.length, groupAddress, MULTICAST_PORT);
                multicastSocket.send(pkt);

                System.out.println("Sending info from the item " + currentItem.getName() + " to the auction");

            } catch (IOException e) {
                System.err.println("Error sending info: " + e.getMessage());
            }
        });
    }

    private void handleBids() {
        executor.submit(() -> {
            while (auctionRunning) {
                try {
                    byte[] buffer = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    multicastSocket.receive(packet);

                    String encryptedMessage = new String(packet.getData(), 0, packet.getLength()).trim();
                    String bidMessage;

                    try {
                        bidMessage = cryptoUtils.decryptMessageAES(encryptedMessage, symmetricKey);
                        System.out.println("Decrypted auction: " + bidMessage);
                    } catch (Exception e) {
                        continue;
                    }

                    if (!bidMessage.startsWith("BID:")) {
                        System.out.println("Ignored message (not a user bid): " + bidMessage);
                        continue;
                    }

                    String[] parts = bidMessage.substring(4).split(",");
                    if (parts.length < 2) {
                        System.err.println("Invalid message format: " + bidMessage);
                        continue;
                    }

                    String bidderCPF = parts[0].split(":")[1].trim();
                    int bidValue = Integer.parseInt(parts[1].split(":")[1].trim());

                    synchronized (this) {
                        if (bidValue > currentHighestBid && bidValue >= currentItem.getMinimumBid()) {
                            currentHighestBid = bidValue;
                            currentHighestBidder = bidderCPF;
                            System.out.println("New highest bid: R$" + currentHighestBid + " by: " + currentHighestBidder);

                            broadcastAuctionInfo("newWinner");
                        }
                    }
                } catch (IOException e) {
                    if (auctionRunning) {
                        System.err.println("Error receiving bid: " + e.getMessage());

                    }
                } catch (ArrayIndexOutOfBoundsException | InterruptedException | NumberFormatException ex) {
                    Logger.getLogger(ServerAuction.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void sendTime(int time) {
        String timeMessage = "TIME_LEFT:" + time;
        byte[] buffer = timeMessage.getBytes();

        try (DatagramSocket socket = new DatagramSocket()) {
            groupAddress = InetAddress.getByName(MULTICAST_ADDRESS);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, groupAddress, MULTICAST_PORT);
            socket.send(packet);
            System.out.println("Time left sended: " + time + " seconds");
        } catch (IOException e) {
            System.err.println("Erroe sending the time left: " + e.getMessage());
        }
    }

    private void announceWinner() throws InterruptedException {
        try {
            String winnerMessage;
            if (currentHighestBid >= currentItem.getMinimumBid()) {
                winnerMessage = "ITEM_FINISHED: Item: " + currentItem.getName() + ". Vencedor: " + currentHighestBidder
                        + ".Lance de R$" + currentHighestBid;
                finalMessage += """
                                
                                Item: """ + currentItem.getName() + ". Vencedor: " + currentHighestBidder
                        + ". Lance de R$" + currentHighestBid;
            } else {
                winnerMessage = "ITEM_FINISHED: Leilão do item \"" + currentItem.getName() + "\" encerrado sem vencedores. Nenhum lance acima do lance mínimo.";
                finalMessage += """
                                
                                Item: """ + currentItem.getName() + "\" encerrado sem vencedores";
            }            
            byte[] buffer = winnerMessage.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, groupAddress, MULTICAST_PORT);
            multicastSocket.send(packet);
            Thread.sleep(4000);

            System.out.println(winnerMessage);
        } catch (IOException e) {
            System.err.println("Error announcing winner: " + e.getMessage());
        }
    }

    private void closeAuction() {
        try {           
            byte[] buffer = finalMessage.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, groupAddress, MULTICAST_PORT);
            multicastSocket.send(packet);

            System.out.println(finalMessage);

            multicastSocket.leaveGroup(groupAddress);
            multicastSocket.close();
            executor.shutdownNow();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            System.out.println("IP do Servidor: " + ip.getHostAddress());
        } catch (UnknownHostException e) {
            System.err.println("Erro ao obter o IP do servidor: " + e.getMessage());
        }
        ServerAuction server = new ServerAuction();
        ExecutorService executorMain = Executors.newFixedThreadPool(2);
        try {
            executorMain.submit(() -> {
                try {
                    server.verifyClient();
                } catch (Exception e) {
                }
            });

            executorMain.submit(() -> {
                try {
                    server.startAuction();
                } catch (InterruptedException e) {
                }
            });

            Thread.currentThread().join();
        } catch (InterruptedException e) {
        } finally {
            executorMain.shutdownNow();
            System.out.println("Server closed.");
        }
    }

}
