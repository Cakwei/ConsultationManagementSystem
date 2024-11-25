package org.cakwei.Gui;
import com.formdev.flatlaf.FlatDarkLaf;
import org.cakwei.Account;
import static org.cakwei.Application.currentSession;

public class Homepage extends javax.swing.JFrame {
    public static final Homepage HomepageGui = new Homepage();
    public Homepage() {
        initComponents();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        sideNavButton1 = new javax.swing.JButton();
        sideNavButton3 = new javax.swing.JButton();
        showUserLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        myProfile = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        body = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(200, 500));

        sideNavButton1.setBackground(new java.awt.Color(153, 204, 255));
        sideNavButton1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        sideNavButton1.setForeground(new java.awt.Color(0, 0, 0));
        sideNavButton1.setText("Book consultation");
        sideNavButton1.setBorder(null);
        sideNavButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sideNavButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sideNavButton1ActionPerformed(evt);
            }
        });

        sideNavButton3.setBackground(new java.awt.Color(153, 204, 255));
        sideNavButton3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        sideNavButton3.setForeground(new java.awt.Color(0, 0, 0));
        sideNavButton3.setText("My consultations");
        sideNavButton3.setBorder(null);
        sideNavButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sideNavButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sideNavButton3ActionPerformed(evt);
            }
        });

        showUserLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        showUserLabel.setForeground(new java.awt.Color(0, 0, 0));
        showUserLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        showUserLabel.setText("<User Code>");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/307642596_466689702154135_5142237392028886965_n (3).png"))); // NOI18N
        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sideNavButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(sideNavButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(showUserLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addGap(25, 25, 25)
                .addComponent(showUserLabel)
                .addGap(50, 50, 50)
                .addComponent(sideNavButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(sideNavButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        myProfile.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        myProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user (4).png"))); // NOI18N
        myProfile.setBorder(null);
        myProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        myProfile.setPreferredSize(new java.awt.Dimension(954, 758));
        myProfile.setOpaque(false);
        myProfile.setContentAreaFilled(false);
        myProfile.setBorderPainted(false);
        myProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myProfileActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("APU Consultation Management System");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 2016, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(myProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(myProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );

        body.setBackground(new java.awt.Color(255, 255, 255));
        body.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1201, Short.MAX_VALUE)
                    .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1250, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void sideNavButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sideNavButton1ActionPerformed
        sideNavButton1.setBackground(new java.awt.Color(105, 181, 255));
        sideNavButton3.setBackground(new java.awt.Color(153, 204, 255));
        body.removeAll();
        body.add(new HomepageBook());
        body.revalidate();
    }//GEN-LAST:event_sideNavButton1ActionPerformed

    private void myProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myProfileActionPerformed
        new profileFrame().setVisible(true);
    }//GEN-LAST:event_myProfileActionPerformed

    private void sideNavButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        sideNavButton1.setBackground(new java.awt.Color(153, 204, 255));
        sideNavButton3.setBackground(new java.awt.Color(105, 181, 255));
        body.removeAll();
        body.add(new HomepageView());
        body.revalidate();
    }
    public static void main(String[] args) {
        FlatDarkLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                HomepageGui.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel body;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton myProfile;
    public javax.swing.JLabel showUserLabel;
    private javax.swing.JButton sideNavButton1;
    private javax.swing.JButton sideNavButton3;
    // End of variables declaration//GEN-END:variables
}
