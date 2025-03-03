
package org.cakwei.Gui;
import org.cakwei.ConsultationManagement;
import org.cakwei.ConsultationStatus;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.cakwei.Gui.Homepage.HomepageGui;

public class rescheduleMenu extends javax.swing.JFrame {
    public rescheduleMenu() {
        new ConsultationManagement().checkConsultationHasPassed();
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        timePicker1 = new com.github.lgooddatepicker.components.TimePicker();
        submitBtn = new javax.swing.JButton();
        lecturerInChargeTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dateTimePicker1 = new com.github.lgooddatepicker.components.DateTimePicker();
        jLabel1 = new javax.swing.JLabel();
        consultationIdTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        submitBtn.setText("Reschedule appointment");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        lecturerInChargeTextField.setEnabled(false);

        jLabel2.setText("Lecturer in-charge:");

        jLabel3.setText("Choose new consultation date:");

        jLabel1.setText("Consultation ID:");

        consultationIdTextField.setText("jTextField1");
        consultationIdTextField.setEnabled(false);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Consultation Rescheduling");
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        jLabel5.setText("Choose consultation duration:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "30 Minutes", "1 Hour", "1.5 Hours", "2 Hours" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(submitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3))
                            .addGap(61, 61, 61))
                        .addComponent(lecturerInChargeTextField)
                        .addComponent(dateTimePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(consultationIdTextField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel4)
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(consultationIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lecturerInChargeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateTimePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(submitBtn)
                .addGap(50, 50, 50))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
       String date = dateTimePicker1.getDatePicker().getDateStringOrEmptyString();
       String time = dateTimePicker1.getTimePicker().getTimeStringOrEmptyString(); // 2024-11-01 02:00
       int index = jComboBox1.getSelectedIndex();
       int[] duration = {30, 60, 90, 120};
       String dateTime = date + " " + time;
       String id = consultationIdTextField.getText();
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
       LocalDateTime convertedTime = LocalDateTime.parse(dateTime, formatter);
        if (!(date.isBlank() || time.isBlank())) {
            new ConsultationManagement().updateConsultationInFile(id, ConsultationStatus.RESCHEDULED, convertedTime, duration[index]);
            JOptionPane.showMessageDialog(null, "Your request for rescheduling has been submitted.", "Rescheduling Request", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            HomepageGui.body.removeAll();
            HomepageGui.body.add(new HomepageView());
            HomepageGui.body.revalidate();
        }
    }//GEN-LAST:event_submitBtnActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new rescheduleMenu().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField consultationIdTextField;
    private com.github.lgooddatepicker.components.DateTimePicker dateTimePicker1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public javax.swing.JTextField lecturerInChargeTextField;
    private javax.swing.JButton submitBtn;
    private com.github.lgooddatepicker.components.TimePicker timePicker1;
    // End of variables declaration//GEN-END:variables
}
