package views;

/**
 *
 * @author Nathan
 */
public class SummaryAuction extends javax.swing.JPanel {

    public SummaryAuction(String finalMessage) {
        initComponents();
        String[] lines = finalMessage.split(":");
        for (int i = 1; i < lines.length; i++) {
            TA_Summary.append(lines[i]);
        }        
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
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 490, 20));

        TA_Summary.setBackground(new java.awt.Color(255, 255, 255));
        TA_Summary.setColumns(20);
        TA_Summary.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        TA_Summary.setForeground(new java.awt.Color(0, 0, 0));
        TA_Summary.setRows(5);
        jScrollPane1.setViewportView(TA_Summary);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 430, 270));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 490, 20));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea TA_Summary;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    // End of variables declaration//GEN-END:variables
}
