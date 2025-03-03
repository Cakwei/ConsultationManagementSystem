package org.cakwei.Gui;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionsPanelHomepageView extends JPanel {
    public ActionsPanelHomepageView() {
        initComponents();
    }
    public void initializeBtn(TableActionEventHomepageView event, int row) {
        giveFeedbackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              event.onClickGiveFeedback(row);
            }
        });
        cancelScheduleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.onClickCancel(row);
            }
        });
        rescheduleConsultationBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.onClickReschedule(row);
            }
        });
        readFeedback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.onClickReadFeedback(row);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        giveFeedbackBtn = new javax.swing.JButton();
        rescheduleConsultationBtn = new javax.swing.JButton();
        cancelScheduleBtn = new javax.swing.JButton();
        readFeedback = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 153, 153));

        giveFeedbackBtn.setText("Give a feedback");
        giveFeedbackBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        rescheduleConsultationBtn.setText("Reschedule consultation");
        rescheduleConsultationBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        cancelScheduleBtn.setText("Cancel Schedule");
        cancelScheduleBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelScheduleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelScheduleBtnActionPerformed(evt);
            }
        });

        readFeedback.setText("Read feedback");
        readFeedback.setPreferredSize(new java.awt.Dimension(113, 23));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(330, Short.MAX_VALUE)
                .addComponent(giveFeedbackBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(readFeedback, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rescheduleConsultationBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelScheduleBtn)
                .addContainerGap(329, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(giveFeedbackBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rescheduleConsultationBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cancelScheduleBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(readFeedback, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cancelScheduleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelScheduleBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelScheduleBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelScheduleBtn;
    private javax.swing.JButton giveFeedbackBtn;
    private javax.swing.JButton readFeedback;
    private javax.swing.JButton rescheduleConsultationBtn;
    // End of variables declaration//GEN-END:variables
}
