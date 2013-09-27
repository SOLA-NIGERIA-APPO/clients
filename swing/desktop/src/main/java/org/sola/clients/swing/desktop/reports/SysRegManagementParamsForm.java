/**
 * ******************************************************************************************
 * Copyright (C) 2012 - Food and Agriculture Organization of the United Nations
 * (FAO). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,this
 * list of conditions and the following disclaimer. 2. Redistributions in binary
 * form must reproduce the above copyright notice,this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. 3. Neither the name of FAO nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT,STRICT LIABILITY,OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 * IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * *********************************************************************************************
 */
package org.sola.clients.swing.desktop.reports;

import java.awt.ComponentOrientation;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import net.sf.jasperreports.engine.JasperPrint;
import org.sola.clients.beans.systematicregistration.*;
import org.sola.clients.reports.ReportManager;
import org.sola.clients.swing.common.LafManager;
import org.sola.clients.swing.common.controls.CalendarForm;
import org.sola.clients.swing.common.tasks.SolaTask;
import org.sola.clients.swing.common.tasks.TaskManager;
import org.sola.clients.swing.desktop.ReportViewerForm;
import org.sola.clients.swing.ui.renderers.FormattersFactory;
import org.sola.common.messaging.ClientMessage;
import org.sola.common.messaging.MessageUtility;

/**
 *
 * @author RizzoM
 */
public class SysRegManagementParamsForm extends javax.swing.JDialog {

    private String location = "";
    private String tmpLocation = "";
    private String whichReport = "";

    /**
     * Creates new form SysRegManagementParamsForm
     */
    public SysRegManagementParamsForm(java.awt.Frame parent, boolean modal, String whichReport) {
        super(parent, modal);
        initComponents();
        this.labSearchArea.setVisible(true);
        this.cadastreObjectSearch.setVisible(true);
        this.whichReport = whichReport;
        if (this.whichReport.contentEquals("sysRegStatusBean")) {
            this.labFromDate.setVisible(false);
            this.labToDate.setVisible(false);
            this.txtFromDate.setVisible(false);
            this.txtToDate.setVisible(false);
            this.btnShowCalendarFrom.setVisible(false);
            this.btnShowCalendarTo.setVisible(false);
        }
        if (this.whichReport.contentEquals("sysRegProductionBean")) {
            this.labFromDate.setVisible(true);
            this.labToDate.setVisible(false);
            this.txtFromDate.setVisible(true);
            this.txtToDate.setVisible(false);
            this.btnShowCalendarFrom.setVisible(true);
            this.btnShowCalendarTo.setVisible(false);
            this.labSearchArea.setVisible(false);
            this.cadastreObjectSearch.setVisible(false);
        }
    }

    /**
     * Creates new form SysRegManagementParamsForm
     */
    public SysRegManagementParamsForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.labSearchArea.setVisible(true);
        this.cadastreObjectSearch.setVisible(true);
        this.whichReport = whichReport;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchParams = new org.sola.clients.beans.systematicregistration.SysRegManagementParamsBean();
        sysRegManagementBean = new org.sola.clients.beans.systematicregistration.SysRegManagementBean();
        sysRegStatusBean = new org.sola.clients.beans.systematicregistration.SysRegStatusBean();
        sysRegProgressBean = new org.sola.clients.beans.systematicregistration.SysRegProgressBean();
        sysRegProductionBean = new org.sola.clients.beans.systematicregistration.SysRegProductionBean();
        searchParams1 = new org.sola.clients.beans.application.LodgementViewParamsBean();
        reportViewerPanel = new org.sola.clients.swing.ui.reports.ReportViewerPanel();
        labHeader = new javax.swing.JLabel();
        txtFromDate = new javax.swing.JFormattedTextField();
        btnShowCalendarFrom = new javax.swing.JButton();
        labFromDate = new javax.swing.JLabel();
        txtToDate = new javax.swing.JFormattedTextField();
        labToDate = new javax.swing.JLabel();
        btnShowCalendarTo = new javax.swing.JButton();
        cadastreObjectSearch = new org.sola.clients.swing.ui.cadastre.LocationSearch();
        labSearchArea = new javax.swing.JLabel();
        viewReport = new javax.swing.JButton();
        statusPanel = new javax.swing.JPanel();
        taskPanel1 = new org.sola.clients.swing.common.tasks.TaskPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labHeader.setBackground(new java.awt.Color(255, 153, 0));
        labHeader.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labHeader.setForeground(new java.awt.Color(255, 255, 255));
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/sola/clients/swing/desktop/reports/Bundle"); // NOI18N
        labHeader.setText(bundle.getString("SysRegManagementParamsForm.labHeader.text")); // NOI18N
        labHeader.setOpaque(true);

        txtFromDate.setFont(new java.awt.Font("Tahoma", 0, 12));
        txtFromDate.setFormatterFactory(FormattersFactory.getInstance().getDateFormatterFactory());
        txtFromDate.setToolTipText(bundle.getString("SysRegManagementParamsForm.txtFromDate.toolTipText")); // NOI18N
        txtFromDate.setComponentOrientation(ComponentOrientation.getOrientation(Locale.getDefault()));
        txtFromDate.setHorizontalAlignment(JTextField.LEADING);
        txtFromDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtFromDatePropertyChange(evt);
            }
        });

        btnShowCalendarFrom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/common/calendar.png"))); // NOI18N
        btnShowCalendarFrom.setText(bundle.getString("SysRegManagementParamsForm.btnShowCalendarFrom.text")); // NOI18N
        btnShowCalendarFrom.setBorder(null);
        btnShowCalendarFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowCalendarFromActionPerformed(evt);
            }
        });

        labFromDate.setText(bundle.getString("SysRegManagementParamsForm.labFromDate.text")); // NOI18N

        txtToDate.setFont(new java.awt.Font("Tahoma", 0, 12));
        txtToDate.setFormatterFactory(FormattersFactory.getInstance().getDateFormatterFactory());
        txtToDate.setToolTipText(bundle.getString("SysRegManagementParamsForm.txtToDate.toolTipText")); // NOI18N
        txtToDate.setComponentOrientation(ComponentOrientation.getOrientation(Locale.getDefault()));
        txtToDate.setHorizontalAlignment(JTextField.LEADING);

        labToDate.setText(bundle.getString("SysRegManagementParamsForm.labToDate.text")); // NOI18N

        btnShowCalendarTo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/common/calendar.png"))); // NOI18N
        btnShowCalendarTo.setText(bundle.getString("SysRegManagementParamsForm.btnShowCalendarTo.text")); // NOI18N
        btnShowCalendarTo.setBorder(null);
        btnShowCalendarTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowCalendarToActionPerformed(evt);
            }
        });

        cadastreObjectSearch.setText(bundle.getString("SysRegManagementParamsForm.cadastreObjectSearch.text")); // NOI18N

        labSearchArea.setText(bundle.getString("SysRegManagementParamsForm.labSearchArea.text")); // NOI18N

        viewReport.setText(bundle.getString("SysRegManagementParamsForm.viewReport.text")); // NOI18N
        viewReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewReportActionPerformed(evt);
            }
        });

        statusPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        statusPanel.setPreferredSize(new java.awt.Dimension(1024, 24));

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(taskPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(taskPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout reportViewerPanelLayout = new javax.swing.GroupLayout(reportViewerPanel);
        reportViewerPanel.setLayout(reportViewerPanelLayout);
        reportViewerPanelLayout.setHorizontalGroup(
            reportViewerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(reportViewerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(reportViewerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(reportViewerPanelLayout.createSequentialGroup()
                        .addComponent(labFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98)
                        .addComponent(labToDate))
                    .addGroup(reportViewerPanelLayout.createSequentialGroup()
                        .addComponent(txtFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnShowCalendarFrom)
                        .addGap(40, 40, 40)
                        .addComponent(txtToDate, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnShowCalendarTo))
                    .addComponent(labSearchArea)
                    .addGroup(reportViewerPanelLayout.createSequentialGroup()
                        .addComponent(cadastreObjectSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewReport)))
                .addContainerGap())
            .addComponent(statusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
        );
        reportViewerPanelLayout.setVerticalGroup(
            reportViewerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportViewerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labHeader)
                .addGap(19, 19, 19)
                .addGroup(reportViewerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(reportViewerPanelLayout.createSequentialGroup()
                        .addGroup(reportViewerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labFromDate)
                            .addComponent(labToDate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(reportViewerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnShowCalendarFrom)
                            .addComponent(txtToDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnShowCalendarTo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labSearchArea)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(reportViewerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cadastreObjectSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewReport))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(reportViewerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(reportViewerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFromDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtFromDatePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFromDatePropertyChange

    private void btnShowCalendarFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowCalendarFromActionPerformed
        showCalendar(txtFromDate);
    }//GEN-LAST:event_btnShowCalendarFromActionPerformed

    private void showCalendar(JFormattedTextField dateField) {
        CalendarForm calendar = new CalendarForm(null, true, dateField);
        calendar.setVisible(true);
    }
    private void btnShowCalendarToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowCalendarToActionPerformed
        showCalendar(txtToDate);
    }//GEN-LAST:event_btnShowCalendarToActionPerformed

    /**
     * Opens {@link ReportViewerForm} to display report.
     */
    private void showReport(JasperPrint report) {

        ReportViewerForm form = new ReportViewerForm(report);
        form.setVisible(true);
        form.setAlwaysOnTop(true);

    }

    private SysRegManagementBean createSysRegManagementBean() {
        if (sysRegManagementBean == null) {
            sysRegManagementBean = new SysRegManagementBean();
        }
        return sysRegManagementBean;
    }

    private SysRegStatusBean createSysRegStatusBean() {
        if (sysRegStatusBean == null) {
            sysRegStatusBean = new SysRegStatusBean();
        }
        return sysRegStatusBean;
    }

    private SysRegProgressBean createSysRegProgressBean() {
        if (sysRegProgressBean == null) {
            sysRegProgressBean = new SysRegProgressBean();
        }
        return sysRegProgressBean;
    }

    private boolean checkMonday(Date finalFrom) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(finalFrom);
        System.out.println("GIORNO SETTIMANA  "+cal.get(Calendar.DAY_OF_WEEK));
        boolean monday = cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
        if (monday==false) {
            MessageUtility.displayMessage(ClientMessage.DATE_MONDAY);
            return monday;
        }
        return monday;
    }

    private void viewReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewReportActionPerformed

        Date tmpFrom = (Date) txtFromDate.getValue();
        Date tmpTo = (Date) txtFromDate.getValue();


        if (txtFromDate.getValue() == null) {
        } else {
            tmpFrom = (Date) txtFromDate.getValue();
            searchParams.setFromDate(tmpFrom);
            searchParams1.setFromDate(tmpFrom);
        }
        if (txtToDate.getValue() == null) {
        } else {
            tmpTo = (Date) txtToDate.getValue();
            searchParams.setToDate(tmpTo);
            searchParams1.setToDate(tmpTo);
        }
// added for base reports on sections        
        if (cadastreObjectSearch.getSelectedElement() != null) {
            this.location = cadastreObjectSearch.getSelectedElement().toString();
            tmpLocation = (this.location);
            searchParams.setNameLastpart(tmpLocation);

        }

        final String finalReport = this.whichReport;
        final Date finalFrom = tmpFrom;
        final Date finalTo = tmpTo;

        SolaTask t = new SolaTask<Void, Void>() {

            @Override
            public Void doTask() {
                setMessage(MessageUtility.getLocalizedMessageText(ClientMessage.PROGRESS_MSG_OPEN_REPORT));

                if (finalReport.contentEquals("sysRegManagementBean")) {
                    sysRegManagementBean.passParameter(searchParams);
                    showReport(ReportManager.getSysRegManagementReport(sysRegManagementBean, finalFrom, finalTo, tmpLocation));
                }

                if (finalReport.contentEquals("sysRegStatusBean")) {
                    sysRegStatusBean.passParameter(searchParams);
                    showReport(ReportManager.getSysRegStatusReport(sysRegStatusBean, finalFrom, finalTo, tmpLocation));
                }

                if (finalReport.contentEquals("sysRegProgressBean")) {
                    sysRegProgressBean.passParameter(searchParams);
                    showReport(ReportManager.getSysRegProgressReport(sysRegProgressBean, finalFrom, finalTo, tmpLocation));
                }

                if (finalReport.contentEquals("sysRegProductionBean")) {
                    if (checkMonday(finalFrom)){
                     sysRegProductionBean.passParameter(searchParams1);
                     showReport(ReportManager.getSysRegProductionReport(sysRegProductionBean, finalFrom, finalTo));
                    }
                }

                return null;
            }
        };
        TaskManager.getInstance().runTask(t);

        this.dispose();
    }//GEN-LAST:event_viewReportActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnShowCalendarFrom;
    private javax.swing.JButton btnShowCalendarTo;
    private org.sola.clients.swing.ui.cadastre.LocationSearch cadastreObjectSearch;
    private javax.swing.JLabel labFromDate;
    private javax.swing.JLabel labHeader;
    private javax.swing.JLabel labSearchArea;
    private javax.swing.JLabel labToDate;
    private org.sola.clients.swing.ui.reports.ReportViewerPanel reportViewerPanel;
    private org.sola.clients.beans.systematicregistration.SysRegManagementParamsBean searchParams;
    private org.sola.clients.beans.application.LodgementViewParamsBean searchParams1;
    private javax.swing.JPanel statusPanel;
    private org.sola.clients.beans.systematicregistration.SysRegManagementBean sysRegManagementBean;
    private org.sola.clients.beans.systematicregistration.SysRegProductionBean sysRegProductionBean;
    private org.sola.clients.beans.systematicregistration.SysRegProgressBean sysRegProgressBean;
    private org.sola.clients.beans.systematicregistration.SysRegStatusBean sysRegStatusBean;
    private org.sola.clients.swing.common.tasks.TaskPanel taskPanel1;
    private javax.swing.JFormattedTextField txtFromDate;
    private javax.swing.JFormattedTextField txtToDate;
    private javax.swing.JButton viewReport;
    // End of variables declaration//GEN-END:variables
}
