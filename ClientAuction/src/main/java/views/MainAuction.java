package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.util.logging.*;
import javax.crypto.SecretKey;
import javax.swing.*;
import model.CryptoUtils;
import model.MulticastClient;
import model.PlaySound;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Nathan
 */
public final class MainAuction extends javax.swing.JPanel {

    private final PlaySound ps = new PlaySound();
    private MulticastClient multicastClient = null;
    private final String symmetricKey;
    private JSONObject finalMessage = new JSONObject();
    private CryptoUtils cryptoUtils = new CryptoUtils();
    private JSONArray finalMessages = new JSONArray();

    public MainAuction(String cpf, int multicastP, String multicastA, String symmetricKey) throws IOException {
        initComponents();
        this.symmetricKey = symmetricKey;
        LB_CPF.setText(cpf);
        startMulticastCommunication(multicastP, multicastA);
        setComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TF_UserWinning = new javax.swing.JTextField();
        LB_Usuario = new javax.swing.JLabel();
        TF_TimeLeft = new javax.swing.JTextField();
        LB_TempoRestante = new javax.swing.JLabel();
        TF_CurrentBid = new javax.swing.JTextField();
        LB_LanceAtual = new javax.swing.JLabel();
        LB_MinValueBetweenBids = new javax.swing.JLabel();
        LB_txtBid = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        LB_CPF1 = new javax.swing.JLabel();
        LB_CPF = new javax.swing.JLabel();
        TF_InicialBid = new javax.swing.JTextField();
        LB_LanceInicial = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        LB_ItemAuctioned = new javax.swing.JLabel();
        BT_SendBid = new javax.swing.JButton();
        TF_BidUser = new javax.swing.JTextField();
        BT_SummaryAuction = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 153, 153));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TF_UserWinning.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        add(TF_UserWinning, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 250, 120, 30));

        LB_Usuario.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        LB_Usuario.setForeground(new java.awt.Color(0, 0, 0));
        LB_Usuario.setText("User winning:");
        add(LB_Usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 260, 120, 20));

        TF_TimeLeft.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        add(TF_TimeLeft, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 180, 120, 30));

        LB_TempoRestante.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        LB_TempoRestante.setForeground(new java.awt.Color(0, 0, 0));
        LB_TempoRestante.setText("Time left:");
        add(LB_TempoRestante, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, 90, 20));

        TF_CurrentBid.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        add(TF_CurrentBid, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 120, 30));

        LB_LanceAtual.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        LB_LanceAtual.setForeground(new java.awt.Color(0, 0, 0));
        LB_LanceAtual.setText("Current bid:");
        add(LB_LanceAtual, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 120, 20));

        LB_MinValueBetweenBids.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        LB_MinValueBetweenBids.setForeground(new java.awt.Color(0, 0, 0));
        LB_MinValueBetweenBids.setText("R$0 *");
        add(LB_MinValueBetweenBids, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, -1, 20));

        LB_txtBid.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        LB_txtBid.setForeground(new java.awt.Color(0, 0, 0));
        LB_txtBid.setText("*Minimum value between bids:");
        add(LB_txtBid, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, -1, 20));

        jLabel1.setFont(new java.awt.Font("Monospaced", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Welcome");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 33, 290, 50));

        LB_CPF1.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        LB_CPF1.setForeground(new java.awt.Color(0, 0, 0));
        LB_CPF1.setText("cpf:");
        add(LB_CPF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, -1, 20));

        LB_CPF.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        LB_CPF.setForeground(new java.awt.Color(0, 0, 0));
        LB_CPF.setText("(cpf)");
        add(LB_CPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 50, -1, 20));

        TF_InicialBid.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        add(TF_InicialBid, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 120, 30));

        LB_LanceInicial.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        LB_LanceInicial.setForeground(new java.awt.Color(0, 0, 0));
        LB_LanceInicial.setText("Inicial bid:");
        add(LB_LanceInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, 20));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 730, 20));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 730, 20));

        LB_ItemAuctioned.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        LB_ItemAuctioned.setForeground(new java.awt.Color(0, 0, 0));
        LB_ItemAuctioned.setText("Waiting...");
        add(LB_ItemAuctioned, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, 270, -1));

        BT_SendBid.setBackground(new java.awt.Color(255, 255, 255));
        BT_SendBid.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        BT_SendBid.setForeground(new java.awt.Color(0, 0, 0));
        BT_SendBid.setText("Send Bid");
        BT_SendBid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BT_SendBidMouseClicked(evt);
            }
        });
        add(BT_SendBid, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 330, -1, -1));
        add(TF_BidUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 90, 30));

        BT_SummaryAuction.setBackground(new java.awt.Color(255, 255, 255));
        BT_SummaryAuction.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        BT_SummaryAuction.setForeground(new java.awt.Color(0, 0, 0));
        BT_SummaryAuction.setText("Summary Auction");
        BT_SummaryAuction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BT_SummaryAuctionMouseClicked(evt);
            }
        });
        add(BT_SummaryAuction, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 380, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void BT_SendBidMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BT_SendBidMouseClicked
        try {
            int userBid = Integer.parseInt(TF_BidUser.getText().trim());
            int currentBid = Integer.parseInt(TF_CurrentBid.getText().replaceAll("[^0-9]", ""));
            if (userBid <= currentBid) {
                JOptionPane.showMessageDialog(this, "The bid vlaue must be higher then the current bid.");
                return;
            }
            int minValue = Integer.parseInt(LB_MinValueBetweenBids.getText().replaceAll("[^0-9]", ""));
            int difValues = userBid - currentBid;

            if (difValues < minValue) {
                JOptionPane.showMessageDialog(this, "Difference between the last and your bid, should be " + minValue + " or more");
            }
            System.out.println(symmetricKey);
            multicastClient.sendBid(LB_CPF.getText(), userBid, symmetricKey);
            TF_BidUser.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please, insert an actual numeric value");
        } catch (Exception ex) {
            Logger.getLogger(MainAuction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BT_SendBidMouseClicked

    private void BT_SummaryAuctionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BT_SummaryAuctionMouseClicked
        System.out.println(finalMessages);
        Views.summaryAuction = new SummaryAuction(finalMessages);
        JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(Views.mainAuction);
        janela.getContentPane().remove(Views.mainAuction);
        janela.add(Views.summaryAuction, BorderLayout.CENTER);
        janela.pack();
    }//GEN-LAST:event_BT_SummaryAuctionMouseClicked

    public void startMulticastCommunication(int multicastP, String multicastA) throws IOException {
        multicastClient = new MulticastClient(multicastA, multicastP, CryptoUtils.convertBase64ToSecretKey(symmetricKey));
        new Thread(() -> {
            multicastClient.startListening(this);
        }).start();
    }

    public void setComponents() {
        TF_CurrentBid.setEditable(false);
        TF_InicialBid.setEditable(false);
        TF_TimeLeft.setEditable(false);
        TF_UserWinning.setEditable(false);
        BT_SummaryAuction.setVisible(false);
        BT_SummaryAuction.setEnabled(false);
    }

    public void startAuctionInfo(String message, String info) {
        try {
            JSONObject jsonMessage = new JSONObject(message);

            if (info.equals("START_AUCTION")) {
                ps.auctionStarted();
            } else {
                ps.newBuyer();
            }
            SecretKey symmetricKeyConverted = cryptoUtils.convertBase64ToSecretKey(symmetricKey);
            LB_ItemAuctioned.setText(cryptoUtils.decryptMessageAES(jsonMessage.getString("item"), symmetricKeyConverted));
            TF_InicialBid.setText(cryptoUtils.decryptMessageAES(jsonMessage.getString("minimumBid"), symmetricKeyConverted));
            LB_MinValueBetweenBids.setText(cryptoUtils.decryptMessageAES(jsonMessage.getString("minimumBetweenBids"), symmetricKeyConverted));
            TF_CurrentBid.setText(cryptoUtils.decryptMessageAES(jsonMessage.getString("currentHighestBid"), symmetricKeyConverted));
            TF_UserWinning.setText(cryptoUtils.decryptMessageAES(jsonMessage.getString("currentHighestBidder"), symmetricKeyConverted));

            BT_SendBid.setEnabled(true);

        } catch (Exception e) {
            System.err.println("Error processing auction info: " + e.getMessage());
        }
    }

    public void newItemInfo(JSONObject jsonMessage) {
        try {
            ps.newItem();

            SecretKey symmetricKeyConverted = cryptoUtils.convertBase64ToSecretKey(symmetricKey);
            // Decrypt and process each key
            String item = jsonMessage.has("item") ? cryptoUtils.decryptMessageAES(jsonMessage.getString("item"), symmetricKeyConverted) : "";
            String minimumBetweenBids = jsonMessage.has("minimumBetweenBids") ? cryptoUtils.decryptMessageAES(jsonMessage.getString("minimumBetweenBids"), symmetricKeyConverted) : "";
            String minimumBid = jsonMessage.has("minimumBid") ? cryptoUtils.decryptMessageAES(jsonMessage.getString("minimumBid"), symmetricKeyConverted) : "";
            String currentHighestBid = jsonMessage.has("currentHighestBid") ? cryptoUtils.decryptMessageAES(jsonMessage.getString("currentHighestBid"), symmetricKeyConverted) : "";
            String currentHighestBidder = jsonMessage.has("currentHighestBidder") ? cryptoUtils.decryptMessageAES(jsonMessage.getString("currentHighestBidder"), symmetricKeyConverted) : "";

            // Process decrypted values and update components
            LB_ItemAuctioned.setText(item);
            TF_InicialBid.setText(minimumBid);
            LB_MinValueBetweenBids.setText(minimumBetweenBids);
            TF_CurrentBid.setText(currentHighestBid);
            TF_UserWinning.setText(currentHighestBidder);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTime(String message) {
        try {
            JSONObject jsonMessage = new JSONObject(message);
            int timeLeft = Integer.parseInt(cryptoUtils.decryptMessageAES(jsonMessage.getString("time"), cryptoUtils.convertBase64ToSecretKey(symmetricKey)));

            TF_TimeLeft.setText(String.valueOf(timeLeft));

            if (timeLeft <= 5) {
                TF_TimeLeft.setForeground(Color.red);
                ps.playBip();
            } else {
                TF_TimeLeft.setForeground(Color.black);
            }

        } catch (Exception e) {
            System.err.println("Error processing time update: " + e.getMessage());
        }
    }

    public void finishedAuction(String message) throws IOException {
        JSONObject auctionFinishJson = new JSONObject(message);
        finalMessage = auctionFinishJson;
        SwingUtilities.invokeLater(() -> {
            LB_ItemAuctioned.setText("Finished Auction.");
            LB_txtBid.setText("");
            LB_txtBid.setText("");
            LB_MinValueBetweenBids.setText("");
            TF_CurrentBid.setText("");
            TF_InicialBid.setText("");
            TF_TimeLeft.setText("");
            TF_UserWinning.setText("");
            BT_SendBid.setEnabled(false);
        });
        multicastClient.stopListening();
        BT_SummaryAuction.setVisible(true);
        BT_SummaryAuction.setEnabled(true);
    }

    public void itemFinished(JSONObject winnerJson) {
        try {
            String winnerMessage = "";

            String item = cryptoUtils.decryptMessageAES(winnerJson.getString("item"), cryptoUtils.convertBase64ToSecretKey(symmetricKey));
            String winner = cryptoUtils.decryptMessageAES(winnerJson.getString("winner"), cryptoUtils.convertBase64ToSecretKey(symmetricKey));
            int bid = 0;
            if (winnerJson.has("bid")) {
                bid = Integer.parseInt(cryptoUtils.decryptMessageAES(winnerJson.getString("bid"), cryptoUtils.convertBase64ToSecretKey(symmetricKey)));
            }
            String message = "";
            if (winnerJson.has("message")) {
                message = cryptoUtils.decryptMessageAES(winnerJson.getString("message"), cryptoUtils.convertBase64ToSecretKey(symmetricKey));
            }

            JSONObject json = new JSONObject();
            json.put("item", item);
            json.put("winner", winner);
            json.put("bid", String.valueOf(bid));
            json.put("message", message);
            finalMessages.put(json);

            winnerMessage += "Item: " + item + "\n";
            if (winnerJson.has("winner") && !winner.equals("Nenhum")) {
                winnerMessage += "Vencedor: " + winner + "\n";
                winnerMessage += "Lance de: R$" + bid;
            } else {
                winnerMessage += message;
            }
            JOptionPane.showMessageDialog(null, winnerMessage, "Winner", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void informClientWaitItem(String message) {
        JSONObject jsonMessage = new JSONObject(message);
        int timeLeft = jsonMessage.getInt("time");

        if (timeLeft == 1) {
            BT_SendBid.setEnabled(true);
            LB_ItemAuctioned.setText("Waiting");
        } else {
            BT_SendBid.setEnabled(false);
            LB_ItemAuctioned.setText("Wait for the item time is over");
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BT_SendBid;
    private javax.swing.JButton BT_SummaryAuction;
    private javax.swing.JLabel LB_CPF;
    private javax.swing.JLabel LB_CPF1;
    private javax.swing.JLabel LB_ItemAuctioned;
    private javax.swing.JLabel LB_LanceAtual;
    private javax.swing.JLabel LB_LanceInicial;
    private javax.swing.JLabel LB_MinValueBetweenBids;
    private javax.swing.JLabel LB_TempoRestante;
    private javax.swing.JLabel LB_Usuario;
    private javax.swing.JLabel LB_txtBid;
    private javax.swing.JTextField TF_BidUser;
    private javax.swing.JTextField TF_CurrentBid;
    private javax.swing.JTextField TF_InicialBid;
    private javax.swing.JTextField TF_TimeLeft;
    private javax.swing.JTextField TF_UserWinning;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables

}
