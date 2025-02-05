package views;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Nathan
 */
public class SummaryAuction extends javax.swing.JPanel {

    public SummaryAuction(JSONArray items) {
        initComponents();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);

            String itemName = item.getString("item");
            String winner = item.getString("winner");
            String bidMessage = "";

            // Verifica se o vencedor é "Nenhum"
            if ("Nenhum".equals(winner)) {
                String messageText = item.getString("message");
                bidMessage = "Leilão encerrado sem vencedores: " + messageText;
            } else {
                // Quando houver um vencedor, pega o lance
                if (item.has("bid") && !item.getString("bid").isEmpty()) {
                    double bid = item.getDouble("bid");
                    bidMessage = "Vencedor: " + winner + " - Lance de: R$" + bid;
                } else {
                    bidMessage = "Vencedor: " + winner + " - Lance não disponível";
                }
            }

            // Adiciona as informações ao resultado
            result.append("\nItem: ").append(itemName)
                    .append("\n").append(bidMessage).append("\n");
        }

        // Preenche o TextArea com o resultado
        TA_Summary.setText(result.toString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        TA_Summary = new javax.swing.JTextArea();
        jSeparator3 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(0, 153, 153));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Monospaced", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Auction Summary");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 33, 240, 50));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 560, 20));

        TA_Summary.setBackground(new java.awt.Color(255, 255, 255));
        TA_Summary.setColumns(20);
        TA_Summary.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        TA_Summary.setForeground(new java.awt.Color(0, 0, 0));
        TA_Summary.setRows(5);
        jScrollPane1.setViewportView(TA_Summary);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 480, 270));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 560, 20));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea TA_Summary;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    // End of variables declaration//GEN-END:variables
}
