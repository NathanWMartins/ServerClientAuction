package views;

import java.io.*;
import javax.swing.*;
import model.*;
import java.util.*;
import java.util.logging.*;
import java.awt.BorderLayout;
import java.net.Socket;
import java.security.PrivateKey;
import org.json.JSONObject;

/**
 *
 * @author Nathan
 */
public class LoginAuction extends javax.swing.JPanel {

    private final KeyManager keyManager;
    private final CryptoUtils cryptoUtils;

    public LoginAuction() throws IOException {
        this.keyManager = new KeyManager();
        this.cryptoUtils = new CryptoUtils();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TF_Message = new javax.swing.JTextField();
        TF_CPF = new javax.swing.JTextField();
        BT_RequestAccess = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 153, 153));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Monospaced", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("CHARITY AUCTION");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, -1, -1));

        jLabel2.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel2.setText("CPF:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, -1, 30));

        jLabel3.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabel3.setText("Message:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 70, 30));

        TF_Message.setBackground(new java.awt.Color(255, 255, 255));
        TF_Message.setForeground(new java.awt.Color(0, 0, 0));
        TF_Message.setText("quero entrar");
        add(TF_Message, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, 200, 30));

        TF_CPF.setBackground(new java.awt.Color(255, 255, 255));
        TF_CPF.setForeground(new java.awt.Color(0, 0, 0));
        TF_CPF.setText("11111111111");
        add(TF_CPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 200, 30));

        BT_RequestAccess.setBackground(new java.awt.Color(102, 102, 102));
        BT_RequestAccess.setForeground(new java.awt.Color(255, 255, 255));
        BT_RequestAccess.setText("REQUEST ACCESS");
        BT_RequestAccess.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BT_RequestAccessMouseClicked(evt);
            }
        });
        add(BT_RequestAccess, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, -1, 40));

        jLabel4.setText(".");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 410, 50, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void BT_RequestAccessMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BT_RequestAccessMouseClicked
        String cpf = TF_CPF.getText().trim();
        String message = TF_Message.getText().trim();
        if (cpf.isEmpty() || message.isEmpty()) {
            JOptionPane.showMessageDialog(this, "CPF and Message must not be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            keyManager.generateAndSaveKeyPair(cpf);
        } catch (IOException ex) {
            Logger.getLogger(LoginAuction.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            System.out.println("Private Key: " + keyManager.getPrivateKey());
            
            byte[] signature = cryptoUtils.generateSignature(message, keyManager.getPrivateKey());
            String signatureBase64 = Base64.getEncoder().encodeToString(signature);

            Socket socket;
            socket = new Socket("127.0.0.1", 6000);
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            JSONObject json = new JSONObject();
            json.put("cpf", cpf);
            json.put("message", message);
            json.put("signature", signatureBase64);

            String jsonString = json.toString();
            System.out.println("Sending message to server...");
            output.println(jsonString);

            // Lê a resposta do servidor
            String serverResponse = input.readLine();
            System.out.println("Server response: " + serverResponse);

            JSONObject responseJson = new JSONObject(serverResponse);

            if (responseJson.getString("status").equals("AUTHORIZED")) {
                // Obtém e decodifica a chave simétrica
                String symmetricKeyBase64 = responseJson.getString("symmetric_key");
                String multicastPortCrypto = responseJson.getString("multicast_port");
                String multicastAddrCrypto = responseJson.getString("multicast_address");
                
                PrivateKey privateKey = keyManager.getPrivateKey();
                
                String symmetricKey = cryptoUtils.decryptSymmetricKey(symmetricKeyBase64, privateKey);
                int multicastPort = Integer.parseInt(cryptoUtils.decryptMessageRSA(multicastPortCrypto, privateKey));
                String multicastAddr = cryptoUtils.decryptMessageRSA(multicastAddrCrypto, privateKey);

                

                Views.mainAuction = new MainAuction(cpf, multicastPort, multicastAddr, symmetricKey);
                JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(Views.viewLogin);
                janela.getContentPane().remove(Views.viewLogin);
                janela.add(Views.mainAuction, BorderLayout.CENTER);
                janela.pack();

            } else {
                System.out.println("Cliente não autorizado.");
            }

            socket.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao enviar requisição: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BT_RequestAccessMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BT_RequestAccess;
    private javax.swing.JTextField TF_CPF;
    private javax.swing.JTextField TF_Message;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
