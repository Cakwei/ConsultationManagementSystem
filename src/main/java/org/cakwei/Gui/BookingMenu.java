/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.cakwei.Gui;

import org.cakwei.Account;
import org.cakwei.Consultation;
import org.cakwei.ConsultationManagement;
import org.cakwei.ConsultationStatus;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import static org.cakwei.Application.currentSession;
import static org.cakwei.Gui.Homepage.HomepageGui;
import static org.cakwei.Gui.HomepageBook.homepageBookTable;

public class BookingMenu extends javax.swing.JFrame {
    public static final BookingMenu BookingMenuGUI = new BookingMenu();
    public BookingMenu(String lecturerName) {
        initComponents();
    }

    public String[] addConsultationRelatedTo(String id) {
        ConsultationManagement s = new ConsultationManagement();
        List<Consultation> x = s.readConsultation();
        List<String> list = new ArrayList<>();
        String[] time = {"30 Minutes", "1 Hour", "1.5 Hours", "2 Hours"};
        List<Long> timeInMinutes = Arrays.asList(30L, 60L, 90L, 120L);
        for (Consultation consultation : x) {
            if (consultation.getStatus().equals(ConsultationStatus.OPEN) && consultation.getLecturerId().equalsIgnoreCase(id)) {
                list.add(consultation.getStartDate().toString() + " " + consultation.getEndDate().toString());
            }
        }
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime startDateTime = LocalDateTime.parse(list.get(i).split(" ")[0], formatter);
            LocalDateTime endDateTime = LocalDateTime.parse(list.get(i).split(" ")[1], formatter);
            long duration = ChronoUnit.MINUTES.between(startDateTime, endDateTime);
            System.out.println(list + " " + duration + " wefipjwefiowejfiopwjf");
            list.set(i, String.format("[%s | %s] ", startDateTime.getDayOfWeek(), time[timeInMinutes.indexOf(duration)]) + startDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm")));
        }
        list.addFirst("Please select a date");
        return list.toArray(new String[0]);
    }
    private String findConsultationId(String lecturerId, LocalDateTime date) {
        ConsultationManagement s = new ConsultationManagement();
        java.util.List<Consultation> consultations = s.readConsultation();
        for (Consultation consultation : consultations) {
            if (consultation.getStatus().equals(ConsultationStatus.OPEN) && consultation.getLecturerId().equalsIgnoreCase(lecturerId) && consultation.getStartDate().equals(date)) {
                return consultation.getConsultationId();
            }
        }
        return null;
    }
    public BookingMenu() {
        new ConsultationManagement().checkConsultationHasPassed();
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        submitBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lecturerHeaderName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        submitBtn.setText("Submit my request");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Choose a date:");

        lecturerHeaderName.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lecturerHeaderName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lecturerHeaderName.setText("<lecturerName>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(submitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))
                .addGap(25, 25, 25))
            .addComponent(lecturerHeaderName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lecturerHeaderName)
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(submitBtn)
                .addGap(50, 50, 50))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        if (jComboBox1.getSelectedItem().equals("Please select a date")){
            JOptionPane.showMessageDialog(null, "Please choose a date", "Error occurred", JOptionPane.ERROR_MESSAGE);
        } else {
            String lecturerName = lecturerHeaderName.getText();
            Account lecturerId = currentSession.findAccountByName(lecturerName);
            String date = jComboBox1.getSelectedItem().toString().split("] ")[1];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm");
            LocalDateTime convertToLocalDateTime = LocalDateTime.parse(date, formatter);
            //new ConsultationManagement().updateConsultationInFile(findConsultationId(lecturerId.getId(), convertToLocalDateTime), ConsultationStatus.SCHEDULED); //GET CONSULT ID && NEW STATUS  findConsultationId(String lecturerName, LocalDateTime date)
            new ConsultationManagement().updateConsultationInFile(findConsultationId(lecturerId.getId(), convertToLocalDateTime), currentSession.currentUserId, ConsultationStatus.SCHEDULED, null, 0, "", "", false);
            DefaultTableModel model = (DefaultTableModel) homepageBookTable.getModel();
            Map<String, Integer> dict = new HomepageBook().filter();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            for (Map.Entry<String, Integer> key : dict.entrySet()) {
                model.addRow(new Object[]{key.getKey(), key.getValue()});
            }
            HomepageGui.body.removeAll();
            HomepageGui.body.add(new HomepageBook());
            HomepageGui.body.revalidate();

            model.fireTableDataChanged();
            JOptionPane.showMessageDialog(null, "You have scheduled a consultation", "Operation Success", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            }
    }//GEN-LAST:event_submitBtnActionPerformed
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookingMenu().setVisible(true);
            }
        });
    }
    public void setHeader(String input) {
        lecturerHeaderName.setText(input);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lecturerHeaderName;
    private javax.swing.JButton submitBtn;
    // End of variables declaration//GEN-END:variables
}
