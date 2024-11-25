package org.cakwei.Gui;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionsPanelHomepageViewLecturer extends JPanel {
    public ActionsPanelHomepageViewLecturer() {
        initComponents();
    }
    public void initializeBtn(TableActionEventHomepageViewLecturer event, int row) {
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
        approveCancelReschedulingBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.onClickApproveCancelRescheduling(row);
            }
        });
        completeEarlyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.onClickCompletedEarly(row);
            }
        });
        viewFeedbackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.onClickViewFeedback(row);
            }
        });

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        giveFeedbackBtn = new javax.swing.JButton();
        approveCancelReschedulingBtn = new javax.swing.JButton();
        cancelScheduleBtn = new javax.swing.JButton();
        completeEarlyBtn = new javax.swing.JButton();
        viewFeedbackBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 153, 153));

        giveFeedbackBtn.setText("Give a feedback");
        giveFeedbackBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        approveCancelReschedulingBtn.setText("Approval/Cancel Rescheduling");
        approveCancelReschedulingBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        cancelScheduleBtn.setText("Cancel Schedule");
        cancelScheduleBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelScheduleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelScheduleBtnActionPerformed(evt);
            }
        });

        completeEarlyBtn.setText("Completed");

        viewFeedbackBtn.setText("Read feedback");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addComponent(giveFeedbackBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(approveCancelReschedulingBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelScheduleBtn)
                .addContainerGap(83, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(viewFeedbackBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(completeEarlyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelScheduleBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(giveFeedbackBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(approveCancelReschedulingBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewFeedbackBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(completeEarlyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cancelScheduleBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_cancelScheduleBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelScheduleBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton approveCancelReschedulingBtn;
    private javax.swing.JButton cancelScheduleBtn;
    private javax.swing.JButton completeEarlyBtn;
    private javax.swing.JButton giveFeedbackBtn;
    private javax.swing.JButton viewFeedbackBtn;
    // End of variables declaration//GEN-END:variables
}
