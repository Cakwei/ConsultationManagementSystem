/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.cakwei.Gui;
import org.cakwei.Consultation;
import org.cakwei.ConsultationManagement;
import org.cakwei.ConsultationStatus;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.cakwei.Application.currentSession;
import static org.cakwei.Gui.HomepageLecturer.HomepageLecturerGui;

public class HomepageViewLecturer extends JPanel {
    static class TableCellRender extends DefaultTableCellRenderer { // ADDED THIS HERE IDK WHY
        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSelected, boolean hasFocus, int row, int column) {
            Component com = super.getTableCellRendererComponent(jtable, o, isSelected, hasFocus, row, column);
            com.setBackground(getRowBackgroundColor(row));
            com.setForeground(getRowForegroundColor(row));
            setHorizontalAlignment(CENTER);
            return com;
        }
    }

    static class TableActionCellRender extends DefaultTableCellRenderer { // ADDED THIS HERE IDK WHY
        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int row, int i1) {
            Component com = super.getTableCellRendererComponent(jtable, o, bln, bln1, row, i1);
            ActionsPanelHomepageViewLecturer action = new ActionsPanelHomepageViewLecturer();
            action.setBackground(getRowBackgroundColor(row));
            return action;
        }
    }
    static class TableActionCellEditor extends DefaultCellEditor {
        private TableActionEventHomepageViewLecturer event;
        public TableActionCellEditor(TableActionEventHomepageViewLecturer event) {
            super(new JCheckBox());
            this.event = event;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            ActionsPanelHomepageViewLecturer action = new ActionsPanelHomepageViewLecturer();
            action.initializeBtn(event, row);
            action.setBackground(getRowBackgroundColor(row));
            return action;
        }
    }
    static Color getRowBackgroundColor(int row) {
        return (row % 2 == 0) ? Color.decode("#808080") : Color.decode("#FFFFFF");
    }
    static Color getRowForegroundColor(int row) {
        return (row % 2 == 0) ? Color.decode("#FFFFFF") : Color.decode("#000000");
    }
    private List<Consultation> filter() {
            ConsultationManagement s = new ConsultationManagement();
            List<Consultation> consultations = s.readConsultation();
            List<Consultation> filteredList = new ArrayList<>();
            for (Consultation consultation : consultations) {
                if ((consultation.getStatus().equals(ConsultationStatus.COMPLETED) || consultation.getStatus().equals(ConsultationStatus.SCHEDULED) || consultation.getStatus().equals(ConsultationStatus.RESCHEDULED)) && consultation.getStudentId().equalsIgnoreCase(currentSession.currentUserId)) {
                    filteredList.add(consultation);
                }
                if (consultation.getLecturerId().equals(currentSession.currentUserId)) {
                    filteredList.add(consultation);
                }
            }
            return filteredList;
    }
    public HomepageViewLecturer() {
        initComponents();
        HomepageViewLecturerTable.setDefaultRenderer(Object.class, new TableCellRender());
        TableActionEventHomepageViewLecturer event = new TableActionEventHomepageViewLecturer() {
            @Override
            public void onClickCancel(int row) { //FOR SCHEDULED, RESCHEDULED
                DefaultTableModel model = (DefaultTableModel) HomepageViewLecturerTable.getModel();
                ConsultationStatus status = ConsultationStatus.valueOf(model.getValueAt(row, 4).toString());
                String id = model.getValueAt(row, 0).toString();
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel?", "Cancellation process", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    if (status.equals(ConsultationStatus.COMPLETED) || status.equals(ConsultationStatus.CANCELLED)) {
                        JOptionPane.showMessageDialog(null, "This consultation period has has already expired/cancelled", "Error occurred", JOptionPane.ERROR_MESSAGE);
                    } else {
                        new ConsultationManagement().updateConsultationInFile(id, ConsultationStatus.CANCELLED);
                        JOptionPane.showMessageDialog(null, "Cancellation has been processed and the student will be notified of this change.", "Cancellation successful", JOptionPane.INFORMATION_MESSAGE);
                        HomepageLecturerGui.body.removeAll();
                        HomepageLecturerGui.body.add(new HomepageViewLecturer());
                        HomepageLecturerGui.body.revalidate();
                    }
                }
            }

            @Override
            public void onClickApproveCancelRescheduling(int row) { //FOR RESCHEDULED STATUS
                DefaultTableModel model = (DefaultTableModel) HomepageViewLecturerTable.getModel();
                String id = model.getValueAt(row, 0).toString();
                String studentName = currentSession.findAccountByName(model.getValueAt(row, 1).toString()).getId();
                ConsultationStatus status = ConsultationStatus.valueOf(model.getValueAt(row, 4).toString());
                Object[] choices = {"Approve", "Cancel"};
                Object defaultChoice = choices[0];
                if (!status.equals(ConsultationStatus.RESCHEDULED)) {
                    JOptionPane.showMessageDialog(null, "Student has not requested a reschedule", "Error occurred", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int result = JOptionPane.showOptionDialog(null, "Do you want to APPROVE or CANCEL this reschedule request? Student will be notified of this change.", "Rescheduling Awaiting Approval", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, defaultChoice);
                if (result == JOptionPane.YES_OPTION) {
                    new ConsultationManagement().updateConsultationInFile(id, studentName, ConsultationStatus.SCHEDULED, null, 0, "", "", true);
                }
                HomepageLecturerGui.body.removeAll();
                HomepageLecturerGui.body.add(new HomepageViewLecturer());
                HomepageLecturerGui.body.revalidate();
            }

            @Override
            public void onClickGiveFeedback(int row) { //FOR COMPLETED STATUS
                DefaultTableModel model = (DefaultTableModel) HomepageViewLecturerTable.getModel();
                String id = model.getValueAt(row, 0).toString();
                ConsultationStatus status = ConsultationStatus.valueOf(model.getValueAt(row, 4).toString());
                boolean isLecturerFeedbackBlank = new ConsultationManagement().findConsultationById(id).getLecturerFeedback().isBlank();
                int result = JOptionPane.showConfirmDialog(null, "Share your experience with us!", "Feedback Process", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    System.out.println(status.equals(ConsultationStatus.COMPLETED) + " " + isLecturerFeedbackBlank + "\n" + new ConsultationManagement().findConsultationById(id).convert() + "\n" + new ConsultationManagement().findConsultationById(id).getStudentFeedback() + new ConsultationManagement().findConsultationById(id).getLecturerFeedback());
                    if (status.equals(ConsultationStatus.COMPLETED) && isLecturerFeedbackBlank) {
                        feedBackMenu feedBackMenu = new feedBackMenu();
                        feedBackMenu.setVisible(true);
                        feedBackMenu.hiddenLabel.setText(id);
                    } else {
                        JOptionPane.showMessageDialog(null, "You have yet to attend this consultation or have already given feedback", "Error occurred", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            @Override
            public void onClickCompletedEarly(int row) {
                DefaultTableModel model = (DefaultTableModel) HomepageViewLecturerTable.getModel();
                String id = model.getValueAt(row, 0).toString();
                ConsultationStatus status = ConsultationStatus.valueOf(model.getValueAt(row, 4).toString());
                if (status.equals(ConsultationStatus.SCHEDULED)) {
                    int result = JOptionPane.showConfirmDialog(null, "Are you sure to end this consultation early?", "Early consultation completion process", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        new ConsultationManagement().updateConsultationInFile(id, ConsultationStatus.COMPLETED);
                        JOptionPane.showMessageDialog(null, "This consultation is declared complete", "Operation success", JOptionPane.INFORMATION_MESSAGE);
                        List<Consultation> filteredList = filter();
                        model.getDataVector().removeAllElements();
                        model.fireTableDataChanged();
                        for (Consultation consultation : filteredList) {
                            if (!consultation.getStatus().equals(ConsultationStatus.CANCELLED)) {
                                String studentName = currentSession.findAccountById(consultation.getStudentId()).getName();
                                model.addRow(new Object[]{consultation.getConsultationId(), studentName, consultation.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm")), consultation.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm")), consultation.getStatus()});
                            }
                        }
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Consultation period has already expired", "Error occurred", JOptionPane.ERROR_MESSAGE);
                HomepageLecturerGui.body.removeAll();
                HomepageLecturerGui.body.add(new HomepageViewLecturer());
                HomepageLecturerGui.body.revalidate();
                return;
            }

            @Override
            public void onClickViewFeedback(int row) {
                DefaultTableModel model = (DefaultTableModel) HomepageViewLecturerTable.getModel();
                String id = model.getValueAt(row, 0).toString();
                ConsultationStatus status = ConsultationStatus.valueOf(model.getValueAt(row, 4).toString());
                List<Consultation> filteredList = filter();
                if (status.equals(ConsultationStatus.COMPLETED)) {
                    for (Consultation consultation : filteredList) {
                        if (consultation.getConsultationId().equals(id)) {
                            viewFeedback viewFeedback = new viewFeedback();
                            viewFeedback.setVisible(true);
                            viewFeedback.studentTextArea.setText(consultation.getStudentFeedback());
                            viewFeedback.lecturerTextArea.setText(consultation.getLecturerFeedback());
                            return;
                        }

                    }
                }
                JOptionPane.showMessageDialog(null, "This consultation period has not started/ended", "Error occurred", JOptionPane.ERROR_MESSAGE);
        }
        };
        HomepageViewLecturerTable.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        HomepageViewLecturerTable.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event));
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        submitBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        HomepageViewLecturerTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        submitBtn.setText("Search");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(0, 51, 204));

        HomepageViewLecturerTable.setBackground(new java.awt.Color(255, 255, 255));
        HomepageViewLecturerTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        HomepageViewLecturerTable.setForeground(new java.awt.Color(0, 0, 0));
        HomepageViewLecturerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Student", "Date/Time", "Duration", "Status", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        HomepageViewLecturerTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        HomepageViewLecturerTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        HomepageViewLecturerTable.setFillsViewportHeight(true);
        HomepageViewLecturerTable.setGridColor(new java.awt.Color(233, 233, 233));
        HomepageViewLecturerTable.setOpaque(false);
        HomepageViewLecturerTable.setRowHeight(80);
        HomepageViewLecturerTable.setSelectionBackground(new java.awt.Color(233, 233, 233));
        HomepageViewLecturerTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        HomepageViewLecturerTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        HomepageViewLecturerTable.setShowGrid(true);
        HomepageViewLecturerTable.setShowVerticalLines(false);
        HomepageViewLecturerTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(HomepageViewLecturerTable);
        if (HomepageViewLecturerTable.getColumnModel().getColumnCount() > 0) {
            HomepageViewLecturerTable.getColumnModel().getColumn(0).setMinWidth(100);
            HomepageViewLecturerTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            HomepageViewLecturerTable.getColumnModel().getColumn(0).setMaxWidth(150);
            HomepageViewLecturerTable.getColumnModel().getColumn(1).setMinWidth(400);
            HomepageViewLecturerTable.getColumnModel().getColumn(1).setPreferredWidth(400);
            HomepageViewLecturerTable.getColumnModel().getColumn(1).setMaxWidth(500);
            HomepageViewLecturerTable.getColumnModel().getColumn(2).setMinWidth(150);
            HomepageViewLecturerTable.getColumnModel().getColumn(2).setPreferredWidth(150);
            HomepageViewLecturerTable.getColumnModel().getColumn(2).setMaxWidth(150);
            HomepageViewLecturerTable.getColumnModel().getColumn(3).setMinWidth(150);
            HomepageViewLecturerTable.getColumnModel().getColumn(3).setPreferredWidth(150);
            HomepageViewLecturerTable.getColumnModel().getColumn(3).setMaxWidth(150);
            HomepageViewLecturerTable.getColumnModel().getColumn(4).setMinWidth(150);
            HomepageViewLecturerTable.getColumnModel().getColumn(4).setPreferredWidth(150);
            HomepageViewLecturerTable.getColumnModel().getColumn(4).setMaxWidth(150);
        }
        List<Consultation> filteredList = filter();
        DefaultTableModel model = (DefaultTableModel) HomepageViewLecturerTable.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        for (Consultation consultation : filteredList) {
            //if (!consultation.getStatus().equals(ConsultationStatus.CANCELLED)) {
                System.out.println("WOINDER:L " + consultation.getConsultationId()  +consultation.getStudentId() +  currentSession.findAccountById(consultation.getStudentId()));
                if (consultation.getStudentId().isBlank()) {
                    model.addRow(new Object[]{consultation.getConsultationId(), "", consultation.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm")), consultation.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm")), consultation.getStatus()});
                     } else {
                    String studentName = currentSession.findAccountById(consultation.getStudentId()).getName();
                    model.addRow(new Object[]{consultation.getConsultationId(), studentName, consultation.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm")), consultation.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm")), consultation.getStatus()});
               // }
            }
        }
        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Filter:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Open","Scheduled", "Rescheduled", "Completed"}));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(10, 10, 10)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(submitBtn)))
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(submitBtn))
                    .addComponent(jLabel1))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                .addGap(25, 25, 25))
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

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        DefaultTableModel model = (DefaultTableModel) HomepageViewLecturerTable.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        int index = jComboBox1.getSelectedIndex();
        String[] options = {"ALL", "OPEN", "SCHEDULED", "RESCHEDULED", "COMPLETED"};
        if (options[index].equals("ALL")) {
            List<Consultation> filteredList = filter();
            for (Consultation consultation: filteredList) {
                if (!consultation.getStatus().equals(ConsultationStatus.CANCELLED)) {
                    if (consultation.getStudentId().isBlank()) {
                        model.addRow(new Object[]{consultation.getConsultationId(), "", consultation.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm")), consultation.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm")), consultation.getStatus()});
                    } else {
                        String studentName = currentSession.findAccountById(consultation.getStudentId()).getName();
                        model.addRow(new Object[]{consultation.getConsultationId(), studentName, consultation.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm")), consultation.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm")), consultation.getStatus()});
                    }
                    }
            }
        } else {
            List<Consultation> filteredList = filter();
            for (Consultation consultation : filteredList) {
                ConsultationStatus consultStatus = consultation.getStatus();
                ConsultationStatus selectedStatus = ConsultationStatus.valueOf(options[index]);
                if (consultStatus.equals(selectedStatus)) {
                    if (consultation.getStudentId().isBlank()) {
                        model.addRow(new Object[]{consultation.getConsultationId(), "", consultation.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm")), consultation.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm")), consultation.getStatus()});
                    } else {
                        String studentName = currentSession.findAccountById(consultation.getStudentId()).getName();
                        model.addRow(new Object[]{consultation.getConsultationId(), studentName, consultation.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm")), consultation.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm")), consultation.getStatus()});
                    }}
                //HomepageLecturerGui.body.add(new HomepageViewLecturer());
                HomepageViewLecturerTable.revalidate();
            }
        }
      }//GEN-LAST:event_submitBtnActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable HomepageViewLecturerTable;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton submitBtn;
    // End of variables declaration//GEN-END:variables
}
