package views;

import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import model.MulticastClient;
import model.PlaySound;

/**
 *
 * @author Nathan
 */
public final class MainAuction extends javax.swing.JPanel {

    private final PlaySound ps = new PlaySound();
    private MulticastClient multicastClient = null;
    private final String symmetricKey;

    public MainAuction(String cpf, int multicastP, String multicastA, String symmetricKey) throws IOException {
        initComponents();
        this.symmetricKey = symmetricKey;
        LB_CPF.setText(cpf);
        startMulticastCommunication(multicastP, multicastA);
        setTextFields();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TF_Usuario = new javax.swing.JTextField();
        LB_Usuario = new javax.swing.JLabel();
        TF_TempoRestante = new javax.swing.JTextField();
        LB_TempoRestante = new javax.swing.JLabel();
        TF_LanceAtual = new javax.swing.JTextField();
        LB_LanceAtual = new javax.swing.JLabel();
        LB_MinValueBetweenBids = new javax.swing.JLabel();
        LB_txtBid = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        LB_CPF1 = new javax.swing.JLabel();
        LB_CPF = new javax.swing.JLabel();
        TF_LanceInicial = new javax.swing.JTextField();
        LB_LanceInicial = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        LB_ItemLeiloado = new javax.swing.JLabel();
        BT_EnviarProposta = new javax.swing.JButton();
        TF_Proposta = new javax.swing.JTextField();

        setBackground(new java.awt.Color(0, 153, 153));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TF_Usuario.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        add(TF_Usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 250, 120, 30));

        LB_Usuario.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        LB_Usuario.setForeground(new java.awt.Color(0, 0, 0));
        LB_Usuario.setText("User winning:");
        add(LB_Usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 260, 120, 20));

        TF_TempoRestante.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        add(TF_TempoRestante, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 180, 120, 30));

        LB_TempoRestante.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        LB_TempoRestante.setForeground(new java.awt.Color(0, 0, 0));
        LB_TempoRestante.setText("Time left:");
        add(LB_TempoRestante, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, 90, 20));

        TF_LanceAtual.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        add(TF_LanceAtual, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 120, 30));

        LB_LanceAtual.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        LB_LanceAtual.setForeground(new java.awt.Color(0, 0, 0));
        LB_LanceAtual.setText("Current bid:");
        add(LB_LanceAtual, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 120, 20));

        LB_MinValueBetweenBids.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        LB_MinValueBetweenBids.setForeground(new java.awt.Color(0, 0, 0));
        LB_MinValueBetweenBids.setText("R$0 *");
        add(LB_MinValueBetweenBids, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, -1, 20));

        LB_txtBid.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        LB_txtBid.setForeground(new java.awt.Color(0, 0, 0));
        LB_txtBid.setText("*Minimum value between bids:");
        add(LB_txtBid, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, -1, 20));

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

        TF_LanceInicial.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        add(TF_LanceInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 120, 30));

        LB_LanceInicial.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        LB_LanceInicial.setForeground(new java.awt.Color(0, 0, 0));
        LB_LanceInicial.setText("Inicial bid:");
        add(LB_LanceInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, 20));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 730, 20));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 730, 20));

        LB_ItemLeiloado.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        LB_ItemLeiloado.setForeground(new java.awt.Color(0, 0, 0));
        LB_ItemLeiloado.setText("Waiting...");
        add(LB_ItemLeiloado, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, 100, -1));

        BT_EnviarProposta.setBackground(new java.awt.Color(51, 51, 51));
        BT_EnviarProposta.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        BT_EnviarProposta.setForeground(new java.awt.Color(255, 255, 255));
        BT_EnviarProposta.setText("Send Bid");
        BT_EnviarProposta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BT_EnviarPropostaMouseClicked(evt);
            }
        });
        add(BT_EnviarProposta, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 330, -1, -1));
        add(TF_Proposta, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 90, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void BT_EnviarPropostaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BT_EnviarPropostaMouseClicked
        try {
            int userBid = Integer.parseInt(TF_Proposta.getText().trim());
            int currentBid = Integer.parseInt(TF_LanceAtual.getText().replaceAll("[^0-9]", ""));

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
            // Envia o lance para o servidor
            multicastClient.sendBid(LB_CPF.getText(), userBid, symmetricKey);
            TF_Proposta.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please, insert an actual numeric value");
        } catch (Exception ex) {
            Logger.getLogger(MainAuction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BT_EnviarPropostaMouseClicked

    public void startMulticastCommunication(int multicastP, String multicastA) throws IOException {
        multicastClient = new MulticastClient(multicastA, multicastP);
        new Thread(() -> {
            multicastClient.startListening(this);
        }).start();
    }

    public void setTextFields() {
        TF_LanceAtual.setEditable(false);
        TF_LanceInicial.setEditable(false);
        TF_TempoRestante.setEditable(false);
        TF_Usuario.setEditable(false);

    }

    public void startAuctionInfo(String message, String info) {
        String[] lines = message.split(",");
        if (info.equals("START_AUCTION")) ps.auctionStarted();
        else ps.newBuyer();
        
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.startsWith("Item em leilão:")) {
                LB_ItemLeiloado.setText(line.substring("Item em leilão:".length()).trim());
                LB_ItemLeiloado.setAlignmentX(CENTER_ALIGNMENT);
            } else if (line.startsWith("Lance mínimo:")) {
                TF_LanceInicial.setText(line.substring("Lance mínimo:".length()).trim());
            } else if (line.startsWith("Valor mínimo entre lances:")) {
                LB_MinValueBetweenBids.setText(line.substring("Valor mínimo entre lances:".length()).trim());
            } else if (line.startsWith("Maior lance atual:")) {
                TF_LanceAtual.setText(line.substring("Maior lance atual:".length()).trim());
            } else if (line.startsWith("Líder:")) {
                TF_Usuario.setText(line.substring("Líder:".length()).trim());
            }
        }

    }

    public void newItemInfo(String message) {
        ps.newItem();

        String[] lines = message.split(",");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.startsWith("Item em leilão:")) {
                LB_ItemLeiloado.setText(line.substring("Item em leilão:".length()).trim());
                LB_ItemLeiloado.setAlignmentX(CENTER_ALIGNMENT);
            } else if (line.startsWith("Lance mínimo:")) {
                TF_LanceInicial.setText(line.substring("Lance mínimo:".length()).trim());
            } else if (line.startsWith("Valor mínimo entre lances:")) {
                LB_MinValueBetweenBids.setText(line.substring("Valor mínimo entre lances:".length()).trim());
            } else if (line.startsWith("Maior lance atual:")) {
                TF_LanceAtual.setText(line.substring("Maior lance atual:".length()).trim());
            } else if (line.startsWith("Líder:")) {
                TF_Usuario.setText(line.substring("Líder:".length()).trim());
            }
        }

    }

    public void updateTime(String message) {
        String[] parts = message.split(":");
        int timeLeft = Integer.parseInt(parts[1]);
        TF_TempoRestante.setText(timeLeft + "s");
        if (timeLeft <= 5) {
            TF_TempoRestante.setForeground(Color.red);
            ps.playBip();
        } else {
            TF_TempoRestante.setForeground(Color.black);
        }

    }

    public void finishedAuction(String message) throws IOException {
        String[] parts = message.split(":");
        SwingUtilities.invokeLater(() -> {
            LB_ItemLeiloado.setText("Finished Auction.");
            LB_txtBid.setText(parts[1]);
            LB_txtBid.setText("");
            LB_MinValueBetweenBids.setText("");
            TF_LanceAtual.setText("");
            TF_LanceInicial.setText("");
            TF_TempoRestante.setText("");
            TF_Usuario.setText("");
            BT_EnviarProposta.setEnabled(false);
        });
        multicastClient.stopListening();
    }

    public void itemFinished(String message) {
        String[] parts = message.split(":");
        String winnerMessage = "";
        for (int i = 1; i < parts.length; i++) {
            winnerMessage += parts[i];
        }
        JOptionPane.showMessageDialog(null, winnerMessage, "Winner", JOptionPane.INFORMATION_MESSAGE);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BT_EnviarProposta;
    private javax.swing.JLabel LB_CPF;
    private javax.swing.JLabel LB_CPF1;
    private javax.swing.JLabel LB_ItemLeiloado;
    private javax.swing.JLabel LB_LanceAtual;
    private javax.swing.JLabel LB_LanceInicial;
    private javax.swing.JLabel LB_MinValueBetweenBids;
    private javax.swing.JLabel LB_TempoRestante;
    private javax.swing.JLabel LB_Usuario;
    private javax.swing.JLabel LB_txtBid;
    private javax.swing.JTextField TF_LanceAtual;
    private javax.swing.JTextField TF_LanceInicial;
    private javax.swing.JTextField TF_Proposta;
    private javax.swing.JTextField TF_TempoRestante;
    private javax.swing.JTextField TF_Usuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables

}
