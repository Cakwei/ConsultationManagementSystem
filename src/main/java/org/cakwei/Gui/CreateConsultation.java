/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.cakwei.Gui;

import org.cakwei.Consultation;
import org.cakwei.ConsultationManagement;
import org.cakwei.ConsultationStatus;

import javax.swing.*;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.cakwei.Application.currentSession;
import static org.cakwei.Gui.HomepageLecturer.HomepageLecturerGui;

public class CreateConsultation extends javax.swing.JPanel {
    public CreateConsultation() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        dateTimePicker1 = new com.github.lgooddatepicker.components.DateTimePicker();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "30 Minutes", "1 Hour", "1.5 Hours", "2 Hours" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Set date & time:");
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));

        jLabel3.setText("Duration: ");
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));

        jButton1.setText("Create consultation");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("CREATE CONSULTATION");
        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(dateTimePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jLabel3)))
                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateTimePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jButton1)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    }//GEN-LAST:event_jComboBox1ActionPerformed
    private boolean within(LocalDateTime toCheck, LocalDateTime startInterval, LocalDateTime endInterval) {
        return !(!toCheck.isBefore(startInterval) && !toCheck.isAfter(endInterval));
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        /*String date = dateTimePicker1.getDatePicker().getDateStringOrEmptyString();
        String time = dateTimePicker1.getTimePicker().getTimeStringOrEmptyString();
        String dateTime = date + " " + time;

        int index = jComboBox1.getSelectedIndex();
        int[] duration = {30, 60, 90, 120};
        boolean isTimeAvailable = true;
        System.out.println(date.isBlank() + " " + dateTimePicker1.getTimePicker().getTimeStringOrEmptyString().isBlank());
        if (!(date.isBlank() || dateTimePicker1.getTimePicker().getTimeStringOrEmptyString().isBlank() /*|| !(index >= 0 && index < duration.length))) {
            List<Consultation> consultations = new ConsultationManagement().readConsultation();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime convertedStartDate = LocalDateTime.parse(dateTime, formatter);
            for (Consultation consultation : consultations) {
                System.out.println("CHECK 1: " + consultation.getStatus().equals(ConsultationStatus.SCHEDULED) + " " + consultation.getStatus().equals(ConsultationStatus.RESCHEDULED) + " " + consultation.getStatus().equals(ConsultationStatus.OPEN) + consultation.getLecturerId().equalsIgnoreCase(currentSession.currentUserId));
                if ((consultation.getStatus().equals(ConsultationStatus.SCHEDULED) || consultation.getStatus().equals(ConsultationStatus.OPEN) || consultation.getStatus().equals(ConsultationStatus.RESCHEDULED)) && consultation.getLecturerId().equalsIgnoreCase(currentSession.currentUserId)) {
                    isTimeAvailable = within(convertedStartDate, consultation.getStartDate(), consultation.getEndDate());
                    System.out.println("BOOLEAN CHECK: " + isTimeAvailable);
                }
            }

            if (isTimeAvailable) {
                new ConsultationManagement().addConsultationToFile(new Consultation(String.valueOf(consultations.size() + 1), "", currentSession.currentUserId, convertedStartDate, convertedStartDate.plusMinutes(duration[index]), "", "", ConsultationStatus.OPEN, false));
                JOptionPane.showMessageDialog(null, "Consultation has been created and students can book for this appointment.", "Creation success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "s", "s", JOptionPane.ERROR_MESSAGE);
            }*/
        String date = dateTimePicker1.getDatePicker().getDateStringOrEmptyString();
        String time = dateTimePicker1.getTimePicker().getTimeStringOrEmptyString();
        String dateTime = date + " " + time;
        int index = jComboBox1.getSelectedIndex();
        int[] duration = {30, 60, 90, 120};
        boolean isTimeAvailable = true;
        if (!date.isBlank() && !time.isBlank() && index >= 0 && index < duration.length) {
            List<Consultation> consultations = new ConsultationManagement().readConsultation();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime convertedStartDate = LocalDateTime.parse(dateTime, formatter);
            LocalDateTime convertedEndDate = convertedStartDate.plusMinutes(duration[index]);
            for (Consultation consultation : consultations) {
                boolean isSameLecturer = consultation.getLecturerId().equalsIgnoreCase(currentSession.currentUserId);
                boolean isScheduled = consultation.getStatus() == ConsultationStatus.SCHEDULED || consultation.getStatus() == ConsultationStatus.OPEN || consultation.getStatus() == ConsultationStatus.RESCHEDULED;
                if (isSameLecturer && isScheduled) {
                    LocalDateTime existingStart = consultation.getStartDate();
                    LocalDateTime existingEnd = consultation.getEndDate();
                    if ((convertedStartDate.isBefore(existingEnd) && convertedEndDate.isAfter(existingStart))) {
                        isTimeAvailable = false;
                        break;
                    }
                }
            }
            if (isTimeAvailable) {
                Consultation newConsultation = new Consultation(String.valueOf(consultations.size() + 1), "", currentSession.currentUserId, convertedStartDate, convertedEndDate, "", "", ConsultationStatus.OPEN, false
                );
                new ConsultationManagement().addConsultationToFile(newConsultation);
                JOptionPane.showMessageDialog(null, "Consultation has been created, and students can book for this appointment.", "Creation Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "The selected time overlaps with another consultation of yours. Please select a different time.", "Time Conflict Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please ensure all fields are filled out correctly.", "Input Error", JOptionPane.ERROR_MESSAGE);
            HomepageLecturerGui.body.removeAll();
            HomepageLecturerGui.body.add(new CreateConsultation());
            HomepageLecturerGui.body.revalidate();
        }//GEN-LAST:event_jButton1ActionPerformed
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.github.lgooddatepicker.components.DateTimePicker dateTimePicker1;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
