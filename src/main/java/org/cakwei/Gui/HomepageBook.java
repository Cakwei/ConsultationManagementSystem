package org.cakwei.Gui;
import org.cakwei.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;

import static org.cakwei.Application.currentSession;
import static org.cakwei.Gui.BookingMenu.BookingMenuGUI;
import org.apache.commons.lang3.StringUtils;
public class HomepageBook extends javax.swing.JPanel {
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
            ActionsPanelHomepageBook action = new ActionsPanelHomepageBook();
            action.setBackground(getRowBackgroundColor(row));
            return action;
        }
    }
    static class TableActionCellEditor extends DefaultCellEditor {
        private TableActionEventHomepageBook event;
        public TableActionCellEditor(TableActionEventHomepageBook event) {
            super(new JCheckBox());
            this.event = event;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            ActionsPanelHomepageBook action = new ActionsPanelHomepageBook();
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
    public Map<String, Integer> filter() {
        ConsultationManagement s = new ConsultationManagement();
        java.util.List<Consultation> x = s.readConsultation();
        Map<String, Integer> dict = new HashMap<>();
        for (Consultation consultation : x) {
            if (consultation.getStatus().equals(ConsultationStatus.OPEN)) {
                System.out.println(consultation.convert());
                String key = new AccountManagement().findAccountById(consultation.getLecturerId()).getName();
                int count = dict.getOrDefault(key, 0);
                dict.put(key, count + 1);
            }
        }
        return dict;
    }
    public HomepageBook() {
        initComponents();
        homepageBookTable.setDefaultRenderer(Object.class, new TableCellRender());
        TableActionEventHomepageBook event = new TableActionEventHomepageBook() {
            @Override
            public void onClickBooking(int row) {
                DefaultTableModel model = (DefaultTableModel) homepageBookTable.getModel();
                Account lecturer = currentSession.findAccountByName(model.getValueAt(row, 0).toString());
                if (!BookingMenuGUI.isVisible()) {
                    BookingMenuGUI.setVisible(true);
                    BookingMenuGUI.setHeader(StringUtils.capitalize(lecturer.getName()));
                    BookingMenuGUI.jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new BookingMenu().addConsultationRelatedTo(lecturer.getId())));
                }
            }
        };
        homepageBookTable.getColumnModel().getColumn(2).setCellRenderer(new TableActionCellRender());
        homepageBookTable.getColumnModel().getColumn(2).setCellEditor(new TableActionCellEditor(event));
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        homepageBookTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        searchBar = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(0, 51, 204));

        homepageBookTable.setBackground(new java.awt.Color(255, 255, 255));
        homepageBookTable.setForeground(new java.awt.Color(0, 0, 0));
        homepageBookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Lecturer Name", "Available slots", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        homepageBookTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        homepageBookTable.setGridColor(new java.awt.Color(233, 233, 233));
        homepageBookTable.setOpaque(false);
        homepageBookTable.setRowHeight(40);
        homepageBookTable.setSelectionBackground(new java.awt.Color(233, 233, 233));
        homepageBookTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        homepageBookTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        homepageBookTable.setShowGrid(true);
        homepageBookTable.setShowVerticalLines(false);
        homepageBookTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(homepageBookTable);
        homepageBookTable.setRowSelectionAllowed(true);
        if (homepageBookTable.getColumnModel().getColumnCount() > 0) {
            homepageBookTable.getColumnModel().getColumn(0).setResizable(false);
            homepageBookTable.getColumnModel().getColumn(1).setResizable(false);
            homepageBookTable.getColumnModel().getColumn(2).setResizable(false);
        }
        Map<String, Integer> dict = filter();
        DefaultTableModel model = (DefaultTableModel) homepageBookTable.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        for (Map.Entry<String, Integer> key : dict.entrySet()) {
            model.addRow(new Object[]{key.getKey(), key.getValue()});
        }

        jPanel2.setBackground(new java.awt.Color(36, 208, 180));

        searchBar.setBackground(new java.awt.Color(255, 255, 255));
        searchBar.setText(" Search lecturer name...");
        searchBar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchBarFocusGained(evt);
            }
        });
        searchBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBarActionPerformed(evt);
            }
        });

        searchBtn.setText("Search");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(searchBtn)
                .addContainerGap(543, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBtn))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                .addGap(15, 15, 15))
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

    private void searchBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBarActionPerformed

    }//GEN-LAST:event_searchBarActionPerformed

    private void searchBarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchBarFocusGained
        searchBar.setText("");
    }//GEN-LAST:event_searchBarFocusGained

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {                                                            
        DefaultTableModel model = (DefaultTableModel) homepageBookTable.getModel();
        model.addRow(new Object[]{"1", "2", "3"});
    }                                      

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        DefaultTableModel model = (DefaultTableModel) homepageBookTable.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        String input = searchBar.getText().toLowerCase(); //read from user file
        Map<String, Integer> list = filter();
        for (Map.Entry<String, Integer> entry : list.entrySet()) {
            if (entry.getKey().toLowerCase().contains(input) && !input.isBlank()) {
            //if (acc.getLecturerName().contains(input) && acc.getStatus().equals(ConsultationStatus.SCHEDULED) && !input.isBlank()) {
                model.addRow(new String[] {entry.getKey(), String.valueOf(entry.getValue())});
            }
        }
    }//GEN-LAST:event_searchBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable homepageBookTable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchBar;
    private javax.swing.JButton searchBtn;
    // End of variables declaration//GEN-END:variables
}

