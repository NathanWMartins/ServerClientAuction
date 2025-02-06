package views;

import java.io.*;
import javax.swing.*;
import model.*;
import java.util.*;
import java.awt.BorderLayout;
import java.net.Socket;
import java.security.PrivateKey;
import org.json.JSONObject;

/**
 *
 * @author Nathan tcp.port == 7000 ip.addr == 239.255.255.1
 */
public class LoginAuction extends javax.swing.JPanel {

    private final KeyManager keyManager;
    private final CryptoUtilsClient cryptoUtils;
    private List<Users> listUsers;

    public LoginAuction() throws IOException {
        this.keyManager = new KeyManager();
        this.cryptoUtils = new CryptoUtilsClient();
        createUsers();
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

        BT_RequestAccess.setBackground(new java.awt.Color(255, 255, 255));
        BT_RequestAccess.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        BT_RequestAccess.setForeground(new java.awt.Color(0, 0, 0));
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

    public final void createUsers() {
        listUsers = Arrays.asList(
                new Users("11111111111",
                        "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwQE2546Kw5F80HCSWAQnjeL03RNzTWMzkVa4ttQU0VBMu88Eq1hBmyOWOjDtckq3RA5lcoE+LCgw5vUV5FKG934TIrFYORCWujflZ7qpkmCDjb0+ePcWQKS8/pPbQtw/2GWJ7HdqH9C0GK5abGM8OJc+kCQ6W8HCts1if/2UrnKI2+L4yrmOo1dpAWoLlzbmXtxFyRxxruTTgTQ7y5KSWVGwOzlqrOzZvP0YjFyzjIfsyVz/IOP5F7IZfK6dja/I1A4Old4qsGslIfmMHesHduXbCM5ZC1bJHqDLtpUkcjSr/A9M0iJQbWv7hA6g/0Q24XUN4B+NtFAs1ki5NhPGjQIDAQAB",
                        "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDBATbnjorDkXzQcJJYBCeN4vTdE3NNYzORVri21BTRUEy7zwSrWEGbI5Y6MO1ySrdEDmVygT4sKDDm9RXkUob3fhMisVg5EJa6N+VnuqmSYIONvT549xZApLz+k9tC3D/YZYnsd2of0LQYrlpsYzw4lz6QJDpbwcK2zWJ//ZSucojb4vjKuY6jV2kBaguXNuZe3EXJHHGu5NOBNDvLkpJZUbA7OWqs7Nm8/RiMXLOMh+zJXP8g4/kXshl8rp2Nr8jUDg6V3iqwayUh+Ywd6wd25dsIzlkLVskeoMu2lSRyNKv8D0zSIlBta/uEDqD/RDbhdQ3gH420UCzWSLk2E8aNAgMBAAECggEADp5s7jUZxZ5rec5NB2WpKVIbgfNLNH1jFosfCpodyYzvgvo3PFM/BOU5J8c09Qc7wmfkvzuS3f1dVuERe23U00I1aNdN+2SKRiids7Grzm6JCF9hMABnKxZPSmkgnnvYSifGvqZ831QLkCMErlkF4rNyBbD4yqJugTIlV9R5Qo1Ai7d9e/Ura2zDY1sBsnxffpL0PGu0mwaXlqQJnsoNeuK07ihqqRRRfsP+CWLaQu7wvzy+Xy8YcXjCsKtr6FkzlF0T+nAo0hnAbGAoaNR1JrnvW5bgrxh6rrk1KS82SX80O8eLUTCfX2F5T3/vFoGkjifcjGUUkri9LbP96rI1gQKBgQDya/P6GrE2h7dzg5Qli1zt5ZU9XgHzF0Ke7cfuSslF0STo/GiMus2U8kA6wOhztA8GnKatxd2zvYTSSGZVkCm5fI+Nlvx6ZwuURJyPinGl8fyIHK5HlTjBDQ4NgQR97jr8Pvvk+L0qehbNe5wuJE2gv4boR5LcwVWVa9Uz3h+dtQKBgQDL0K3z/UCxMUZ+C9ezbe+8FiJgHzGLp1pIhZ8JE62kkT3o00WVDMkdcE7f/ieHim84XsM7FJsFlLMmH/UeVSNAxP9qH3eyO6A1k7B93bEum9swZOSk2oSrfNjizf2YqGkyk2rqzW8FW3aP9+FL8qX6MPez62RqaOgH6/AjA17MeQKBgQCdtufyphz22hLa3xap2mIqD7wpQZjJGy/nj9EL0bHiby+xOE2YiQuoxHZPAyP36oQADDhQQ7N59WmNGTcioXjTyRrnxfwaciHRY1Xr+Oj5Sla+AtLLlWRoDGNBG9fdSfksFJnynHUNRoLBSpMQXeP3GcPeKHp+jnVskcwSQ4eUJQKBgFPpeJTpgYbySJmAerAO5RLE8iYs8ZMTtaICEXYFOgp1Gc4Pnag9+Vc7c93Yn6G4Jw5IRYy/cQudKxzZL2vrlXYHJTkl93vT/KPSSGmpqlcMJ/QGtfQBW3nXDtxh/rSpMZZ3Bx/gsIK+I1QmtAU2w4r8Oh9DHLaKtmz8gIOJ0a/RAoGBAN1QPTeXOFlJZ2JjVKfQBRroPoPcMfL4Ts5mZXDaRA2GNMypwy7SjvZBlR3Y6zN+34EuKn3LtA9Wp9CMbHpuqCThB1RmxN8pafNQfxYKS3DTvC//kSNdBK+5GGoWKBr4oovF7ZCH7eWtrNq4fy/wXC5nyGtdGmYAbKgfbsC4AJ6E"),
                new Users("22222222222",
                        "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAu54fYijPozPW7sDGLgFwQZ8d12edkxl4MBaFRMMgwuQ/m0kDjueg+czWiZtRHb3TJV6LnVxhJSg2p2W7EMODO7a/A223EBgZI2UkN+BKGpvvNHu/bf/XO+rOWzafKLdt5+8OY59xHRrmDri4AaunKaPNggNbXtg4tF1IO0Bz2fv7kQF3niSF3bkImNU9RE8HgU25nXrJe6N+Vtli26lMzw3LrckRu2Ue9JSeaCXJtTqISHcsC4fRa8EN9rKbCx9yphkfmGg6VlLqvk0r1KCwCkWGnG3AC1p4b1flvjgCGTlW9KgZB8ccOXsG/5WXirxqJMAirD/RdsF+IkHmI5W4qwIDAQAB",
                        "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC7nh9iKM+jM9buwMYuAXBBnx3XZ52TGXgwFoVEwyDC5D+bSQOO56D5zNaJm1EdvdMlXoudXGElKDanZbsQw4M7tr8DbbcQGBkjZSQ34Eoam+80e79t/9c76s5bNp8ot23n7w5jn3EdGuYOuLgBq6cpo82CA1te2Di0XUg7QHPZ+/uRAXeeJIXduQiY1T1ETweBTbmdesl7o35W2WLbqUzPDcutyRG7ZR70lJ5oJcm1OohIdywLh9FrwQ32spsLH3KmGR+YaDpWUuq+TSvUoLAKRYacbcALWnhvV+W+OAIZOVb0qBkHxxw5ewb/lZeKvGokwCKsP9F2wX4iQeYjlbirAgMBAAECggEAG/dpBC/DK7epfTNdFzR9sDpwr1Axq+CrCrgAq4xOkN6HILWUgzPByhqfjCmLwNwv4i0isJU5odlJtFA0vz31jOwjsvYiyJJfBOipqm6TfT3rPS6JDXOKeNL6aZxNwzr8dgsrYOw+ZzFAQ8eDHjShGVLialPnxc6DPVd+Ud68pixotFp4iPdOgV4WYrctj5Rj7T4oALHwKzeXik44epcgoAydEwbhHDcYW1IVfUcZMUSjPzKQgrE3iH1D9ykZ3dIH0hF4QhtfjgUhDdjWOQDQd+ypu6wF9dEdV88B781GEfoQwrsWeqvfmbhtWzVO0bs8FZTYWiRAchE6L6W8jhEMwQKBgQDCD16BRnRHJ31y9ZOp7UKiw+vlvccTtZAh9ahR85qXJnCYfURl7zLCelEDWd4LqOSZssSkhzZxxL4silW5iqa4ktbCNhzdIabHva0p88HABzAS80rvyMlpdyqq6grCWlg8DGt3nA85mvt7ObMDkdP2Lec9ITd6rJ1z6Lflof9/4QKBgQD3gFkQ7fQ8SIWK31s/4eK2uD5kPjMz3qmaOhl8pD+TsR/h/RQQKWCmriOdKz95se6DHvdGDJfBWoXr66Py9OeVudATIQ2Ai+RfF4gHEj2hnM0HeeubxmCnLA3XrDv60ao87VMb9pjt+ghjZm2EnWx690S1CY/2ZeNA8d6WZ+p6CwKBgQC8zRkj6vjb9Uau3tAQRf5LvxO2DCIwQbf94jQlrzzbC5IGOfGevs6CuWAyQ2+tpl7qTivU6pwEqeZgDSKEBqpX7ry0bNM+LSxHLnnX1AjPm+6gsUVZ7t5OFptTd6qKFj28BlVUJGgHoopDBArvUSSIYXaVWcjRODlj+KlZ53dd4QKBgQDkbhJGPMjphqmyZbmreUktCVmUXZAWdJCmpq71Sgbfwl/BzTniPL5WuGxoWA0qvZ18aW0huuepbmoiN4dG6uB/YVL5BM2YcgTh+y7yD9TQP49AMjfRu0Njp5ZTE0T8ltx/hS7qpSu6oMVzMIsuFbmUuHkyedPkP1+ChnnA6cIQxwKBgCSV/VHrJPNJs+9DJ73hHU65dDcs3YViI3sCvitJiuokMXnTAev340rMvJ7AKVdwhxGtCEZNYiWJFAYszczPdwgmhocgdQTmIcMKFLuj1DX+CswslHaNQDEVhLahMy9yGSznWsfanWeu22U5bz/bawQVw8QbrqatlliyPkunHwam"),
                new Users("33333333333",
                        "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp3NsyW3wFF66x2SuHf/YxHlFfeX1HzxyFwmBDDarxAGq0VxwLJdRv/mBeaM9k4Nru2wMCCUiWAIGmh183AYwMJU7HQTe0/aw/7AEwRxTBq8clzvRrx5RfHiPvc8pqeghDy0jx6Gl3Ky7+9NPipcwW8mVJRpYIDlQeFRi7eZvx772XpMjs0+mC0pnSJWm8ReqWi4aQFs4zjVz3E8xuWk+mGZSoo1ZsOUhP98zROl3A53GqCw/8qq0lnM7j4UlHbPiRwkRuAPx4Z591qDengKepmY5Suq7wfObZgyHlxxe+pFO45a5w1C63AzKWJh2KCUI6Zad8N0LKhHFw3URj054CQIDAQAB",
                        "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCnc2zJbfAUXrrHZK4d/9jEeUV95fUfPHIXCYEMNqvEAarRXHAsl1G/+YF5oz2Tg2u7bAwIJSJYAgaaHXzcBjAwlTsdBN7T9rD/sATBHFMGrxyXO9GvHlF8eI+9zymp6CEPLSPHoaXcrLv700+KlzBbyZUlGlggOVB4VGLt5m/HvvZekyOzT6YLSmdIlabxF6paLhpAWzjONXPcTzG5aT6YZlKijVmw5SE/3zNE6XcDncaoLD/yqrSWczuPhSUds+JHCRG4A/Hhnn3WoN6eAp6mZjlK6rvB85tmDIeXHF76kU7jlrnDULrcDMpYmHYoJQjplp3w3QsqEcXDdRGPTngJAgMBAAECggEALOla6K2pYjVEIF2jC53G3G9wUYOfWnjiWsZsS8PQDvqpf4ys83DEdtprPR4o0ktB1lt04KxhCTzF24kEJ5krTKSB3dckErLMJ0/k/oxjwluuLevX4gGeNmW5m92X3Z/KWiEvwwfajhTwOGslHtVNHZkz/OcTctUcfaGYhRk0qtxfyuryCM8trvWkIcf7H/y973GUgBZ5Wu08Ne/QIW9/ZFn3sqIbVtN3f5YSF7yHqZ9svUiJzWFYMBcBaMH1fdzAoeSPXdWHii/q+Zc2J5H/QREPhb1UANQTrDvrOfSdgQeElHcBk70ZYSvWxNvE157jdYk5HUnEJzkyr3vEvWraPwKBgQC5n6onLFk7quZcaaqjvBcTJo93rWi79z2xfx7SkqJVT18vu8jJvAn1gbilBqmMi857tetKzF9ga5cKn+QOH9BRXSF4SKDpgb/XOLoExXpmX08NiZfe7KtB+ozGBlYTMuBHodpzQHScx9vxJnLEU0c56Rd0vXoyjSrCcYtcieMONwKBgQDm7+/ERuvVuBGuaXFLbkUqrLv5pnEM32CTKqiXAEZmgIiaWsBtMWi6m7hancfqq3Anq0lHnNCGBW15nxs+Cmc31JZdTTTb8ZHAC6lDYVoiZsq65qzopG783jRQliiIAfZJRMkOBzN03plgKmJQj0ajv2v6VVgP8wHikA75K8mLvwKBgQCbYT6iWeo8GxuHod/3/TtniXCwolS4ewaGbL2VUK9YL/+iylMSIzhG7RZt9Xy5rFHklmxmCVuRL56Ygyz4ccMESKv6rvkbXcQDWXXFBrUluoRG9bVOIthce2mZXbZxjbXV4HMm5H54uoeufhu1oWxO2oIK84y1ghuX9knM5ZNN0wKBgHo2G5KpXYC6alD8aAJdFqlgxBF3rXf8dmUrPBC9CoSQHLpisFaYwS2P3t3FhdiAZVf440zMheWG1cp5EregVcNL807o3sJOcq1I/ogz6rkt4LdL/9EVw8554QGMlWJ0d1uK1UNhOC/u5QfpJiIv0FgzovbVV0cJeMPnLKVcMMqNAoGANeDkY/kp94Pa/gSzHgDUmGKnBPrSrdY1tkiK3f6RfM3fc53YQ9Sq8WhDvFyfSK/0g7IWLIoujh1JXowKoN9NRvlPbnCj1wK6N1s50//N1lEuqF7agv7zEWuSWMxEWmJ3PgTUDFftiRhlp3gffWA/9xN404m5aKxuKeMH/gnFY0Q=")
        );
    }

    private void BT_RequestAccessMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BT_RequestAccessMouseClicked
        String cpf = TF_CPF.getText().trim();
        String message = TF_Message.getText().trim();
        if (cpf.isEmpty() || message.isEmpty()) {
            JOptionPane.showMessageDialog(this, "CPF and Message must not be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Users user = new Users();
        for (Users users : listUsers) {
            if (cpf.equals(users.getCpf())) {
                user = users;
                break;
                }
        }
        keyManager.savePublicKeyBase64(cpf, user.getPublicKeyBase64());

        try {
            byte[] signature = cryptoUtils.generateSignature(message, cryptoUtils.convertBase64ToPrivateKey(user.getPrivateKeyBase64()));
            String signatureBase64 = Base64.getEncoder().encodeToString(signature);
                                    
            Socket socket;
            socket = new Socket("192.168.107.141", 7000);
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

                //PrivateKey privateKey = keyManager.getPrivateKey();
                PrivateKey privateKey = cryptoUtils.convertBase64ToPrivateKey(user.getPrivateKeyBase64());
                String symmetricKey = cryptoUtils.decryptSymmetricKey(symmetricKeyBase64, privateKey);
                int multicastPort = Integer.parseInt(cryptoUtils.decryptMessageRSA(multicastPortCrypto, privateKey));
                String multicastAddr = cryptoUtils.decryptMessageRSA(multicastAddrCrypto, privateKey);

                Views.mainAuction = new MainAuction(cpf, multicastPort, multicastAddr, symmetricKey);
                JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(Views.viewLogin);
                janela.getContentPane().remove(Views.viewLogin);
                janela.add(Views.mainAuction, BorderLayout.CENTER);
                janela.pack();

            } else {
                System.out.println("Unauthorized client");
                JOptionPane.showMessageDialog(this, "Unauthorized client ", "Erro", JOptionPane.ERROR_MESSAGE);
            }

            socket.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error sending requisition: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
