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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static org.cakwei.Application.currentSession;
import static org.cakwei.Gui.Homepage.HomepageGui;

public class HomepageView extends javax.swing.JPanel {

      public static String[] timeIncrement30Minutes() {
            List<String> list = new ArrayList<>();
            LocalTime startTime = LocalTime.of(0, 0);
            LocalTime endTime = LocalTime.of(23, 30);
            while (startTime.isBefore(endTime)) {
                list.add(startTime.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm")));
                startTime = startTime.plusMinutes(30);
            }
            list.addLast("23:30");
          return list.toArray(new String[0]);
      }

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
            ActionsPanelHomepageView action = new ActionsPanelHomepageView();
            action.setBackground(getRowBackgroundColor(row));
            return action;
        }
    }
    static class TableActionCellEditor extends DefaultCellEditor {
        private TableActionEventHomepageView event;
        public TableActionCellEditor(TableActionEventHomepageView event) {
            super(new JCheckBox());
            this.event = event;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            ActionsPanelHomepageView action = new ActionsPanelHomepageView();
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
                if (((consultation.getStatus().equals(ConsultationStatus.COMPLETED) || consultation.getStatus().equals(ConsultationStatus.SCHEDULED) || consultation.getStatus().equals(ConsultationStatus.RESCHEDULED)) && consultation.getStudentId().equalsIgnoreCase(currentSession.currentUserId))) {
                    filteredList.add(consultation);
                }
                if (consultation.getLecturerId().equals(currentSession.currentUserId)) {
                    filteredList.add(consultation);
                }
            }
            return filteredList;
    }
    public HomepageView() {
        initComponents();
        HomepageViewTable.setDefaultRenderer(Object.class, new TableCellRender());
        TableActionEventHomepageView event = new TableActionEventHomepageView() {
            @Override
            public void onClickCancel(int row) {
                DefaultTableModel model = (DefaultTableModel) HomepageViewTable.getModel();
                ConsultationStatus status = ConsultationStatus.valueOf(model.getValueAt(row, 4).toString());
                String id = model.getValueAt(row, 0).toString();
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel?", "Cancellation process", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    if (status.equals(ConsultationStatus.COMPLETED)) {
                        JOptionPane.showMessageDialog(null, "This consultation period has already expired.", "Error occurred", JOptionPane.ERROR_MESSAGE);
                    } else {
                        new ConsultationManagement().updateConsultationInFile(id, ConsultationStatus.CANCELLED);
                        HomepageGui.body.removeAll();
                        HomepageGui.body.add(new HomepageView());
                        HomepageGui.body.revalidate();
                        }
                    }
                }
            @Override
            public void onClickReschedule(int row) {
                DefaultTableModel model = (DefaultTableModel) HomepageViewTable.getModel();
                String id = model.getValueAt(row, 0).toString();
                String lecturerName = model.getValueAt(row, 1).toString();
                ConsultationStatus status = ConsultationStatus.valueOf(model.getValueAt(row, 4).toString());
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to reschedule this consultation?!", "Reschedule Process", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    if (status.equals(ConsultationStatus.COMPLETED)) {
                        JOptionPane.showMessageDialog(null, "This consultation period has already expired.", "Error occurred", JOptionPane.ERROR_MESSAGE);
                    } else {
                        rescheduleMenu rescheduleMenu = new rescheduleMenu();
                        rescheduleMenu.consultationIdTextField.setText(id);
                        rescheduleMenu.lecturerInChargeTextField.setText(lecturerName);
                        rescheduleMenu.setVisible(true);
                    }
                }
            }

            @Override
            public void onClickGiveFeedback(int row) {
                DefaultTableModel model = (DefaultTableModel) HomepageViewTable.getModel();
                String id = model.getValueAt(row, 0).toString();
                ConsultationStatus status = ConsultationStatus.valueOf(model.getValueAt(row, 4).toString());
                boolean isStudentFeedbackBlank = new ConsultationManagement().findConsultationById(id).getStudentFeedback().isBlank();
                int result = JOptionPane.showConfirmDialog(null, "Share your experience with us!", "Feedback Process", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    if (status.equals(ConsultationStatus.COMPLETED) && isStudentFeedbackBlank) {
                        feedBackMenu feedBackMenu =  new feedBackMenu();
                        feedBackMenu.setVisible(true);
                        feedBackMenu.hiddenLabel.setText(id);
                    } else {
                        JOptionPane.showMessageDialog(null, "You have yet to attend this consultation or have already given feedback", "Error occurred", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        };
        HomepageViewTable.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        HomepageViewTable.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event));
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        submitBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        HomepageViewTable = new javax.swing.JTable();
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

        HomepageViewTable.setBackground(new java.awt.Color(255, 255, 255));
        HomepageViewTable.setForeground(new java.awt.Color(0, 0, 0));
        HomepageViewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Lecturer", "Start Time", "End Time", "Status", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        HomepageViewTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        HomepageViewTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        HomepageViewTable.setFillsViewportHeight(true);
        HomepageViewTable.setGridColor(new java.awt.Color(233, 233, 233));
        HomepageViewTable.setOpaque(false);
        HomepageViewTable.setRowHeight(40);
        HomepageViewTable.setSelectionBackground(new java.awt.Color(233, 233, 233));
        HomepageViewTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        HomepageViewTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        HomepageViewTable.setShowGrid(true);
        HomepageViewTable.setShowVerticalLines(false);
        HomepageViewTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(HomepageViewTable);
        if (HomepageViewTable.getColumnModel().getColumnCount() > 0) {
            HomepageViewTable.getColumnModel().getColumn(0).setMinWidth(50);
            HomepageViewTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            HomepageViewTable.getColumnModel().getColumn(0).setMaxWidth(50);
            HomepageViewTable.getColumnModel().getColumn(1).setMinWidth(300);
            HomepageViewTable.getColumnModel().getColumn(1).setPreferredWidth(300);
            HomepageViewTable.getColumnModel().getColumn(1).setMaxWidth(300);
            HomepageViewTable.getColumnModel().getColumn(2).setMinWidth(150);
            HomepageViewTable.getColumnModel().getColumn(2).setPreferredWidth(150);
            HomepageViewTable.getColumnModel().getColumn(2).setMaxWidth(150);
            HomepageViewTable.getColumnModel().getColumn(3).setMinWidth(150);
            HomepageViewTable.getColumnModel().getColumn(3).setPreferredWidth(150);
            HomepageViewTable.getColumnModel().getColumn(3).setMaxWidth(150);
            HomepageViewTable.getColumnModel().getColumn(4).setMinWidth(150);
            HomepageViewTable.getColumnModel().getColumn(4).setPreferredWidth(150);
            HomepageViewTable.getColumnModel().getColumn(4).setMaxWidth(150);
            HomepageViewTable.getColumnModel().getColumn(5).setPreferredWidth(350);
        }
        List<Consultation> filteredList = filter();
        DefaultTableModel model = (DefaultTableModel) HomepageViewTable.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        for (Consultation consultation : filteredList) {
            String lecturerName = currentSession.findAccountById(consultation.getLecturerId()).getName();
            model.addRow(new Object[]{consultation.getConsultationId(), lecturerName, consultation.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm")), consultation.getEndDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm")), consultation.getStatus()});
        }

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Filter:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Scheduled", "Rescheduled", "Completed"}));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
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
        DefaultTableModel model = (DefaultTableModel) HomepageViewTable.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        int index = jComboBox1.getSelectedIndex();
        String[] options = {"ALL", "SCHEDULED", "RESCHEDULED", "COMPLETED"};
        if (options[index].equals("ALL")) {
            List<Consultation> filteredList = filter();
            for (Consultation consultation: filteredList) {
                String lecturerName = currentSession.findAccountById(consultation.getLecturerId()).getName();
                model.addRow(new Object[]{consultation.getConsultationId(), lecturerName, consultation.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm")), consultation.getEndDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm")), consultation.getStatus()});
            }
        } else {
            List<Consultation> filteredList = filter();
            for (Consultation consultation : filteredList) {
                ConsultationStatus consultStatus = consultation.getStatus();
                ConsultationStatus selectedStatus = ConsultationStatus.valueOf(options[index]);
                if (consultStatus.equals(selectedStatus)) {
                    String lecturerName = currentSession.findAccountById(consultation.getLecturerId()).getName();
                    model.addRow(new Object[]{consultation.getConsultationId(), lecturerName, consultation.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm")), consultation.getEndDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm")), consultation.getStatus()});
                }
            }
        }
      }//GEN-LAST:event_submitBtnActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable HomepageViewTable;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton submitBtn;
    // End of variables declaration//GEN-END:variables
}
