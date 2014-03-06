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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import org.sola.clients.beans.converters.TypeConverters;
import org.sola.clients.beans.digitalarchive.DocumentBean;
import org.sola.clients.beans.source.SourceBean;
import org.sola.clients.beans.systematicregistration.SysRegWorkUnitBean;
import org.sola.clients.beans.validation.ValidationResultBean;
import org.sola.clients.reports.ReportManager;
import org.sola.clients.swing.common.controls.CalendarForm;
import org.sola.clients.swing.common.tasks.SolaTask;
import org.sola.clients.swing.common.tasks.TaskManager;
import org.sola.clients.swing.common.utils.BindingTools;
import org.sola.clients.swing.desktop.ReportViewerForm;
import org.sola.clients.swing.ui.renderers.FormattersFactory;
import org.sola.clients.swing.ui.validation.ValidationResultForm;
import org.sola.common.FileUtility;
import org.sola.common.messaging.ClientMessage;
import org.sola.common.messaging.MessageUtility;
import org.sola.services.boundary.wsclients.WSManager;
import org.sola.webservices.transferobjects.cadastre.SysRegWorkUnitTO;

/**
 *
 * @author rizzom
 */
public class SysRegWorkUnitForm extends javax.swing.JDialog {

    private String location;
    private String tmpLocation = "";
    private ResourceBundle resourceBundle;
    public final static String SELECTED_PARCEL = "selectedParcel";

    /**
     * Creates new form SysRegWorkUnitForm
     */
    public SysRegWorkUnitForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
  
    
     /** 
     * Form constructor. 
     * @param savePartyOnAction Boolean flag to indicate whether to save party 
     * when Save button is clicked.
     * @param partyBean {@link PartyBean} instance to display.
     * @param readOnly Indicates whether to allow any changes on the form.
     */
   public SysRegWorkUnitForm(java.awt.Frame parent, boolean modal, String location) {
        super(parent, modal);
        this.location = location;
        resourceBundle = java.util.ResourceBundle.getBundle("org/sola/clients/swing/desktop/cadastre/Bundle");
        initComponents();
        if (sysRegWorkUnitBean != null) {
            headerPanel.setTitleText(sysRegWorkUnitBean.getName());
        } else {
            headerPanel.setTitleText(resourceBundle.getString("SysRegWorkUnitForm.headerPanel.titleText"));
        }
        
  }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        sysRegWorkUnitBean = createSysRegWorkUnitBean();
        selectionPanel = new javax.swing.JPanel();
        cadastreObjectSearch = new org.sola.clients.swing.ui.cadastre.LocationSearch();
        configureSRWU = new javax.swing.JButton();
        labHeader = new javax.swing.JLabel();
        srwuPanel = new javax.swing.JPanel();
        labNotificationFrom = new javax.swing.JLabel();
        txtFromDate = new javax.swing.JFormattedTextField();
        btnShowCalendarFrom = new javax.swing.JButton();
        panelRecorded = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtEstimatedParcel = new javax.swing.JTextField();
        txtRecordedParcel = new javax.swing.JTextField();
        txtRecordedClaims = new javax.swing.JTextField();
        panelScanned = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtScannedDemarcation = new javax.swing.JTextField();
        txtScannedClaims = new javax.swing.JTextField();
        txtDistributedCertificates = new javax.swing.JTextField();
        srwuToolBar = new javax.swing.JToolBar();
        btnSave1 = new org.sola.clients.swing.common.buttons.BtnSave();
        jButton1 = new javax.swing.JButton();
        labHeader1 = new javax.swing.JLabel();
        headerPanel = new org.sola.clients.swing.ui.HeaderPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configure SR Work Unit");

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/sola/clients/swing/desktop/reports/Bundle"); // NOI18N
        cadastreObjectSearch.setText(bundle.getString("SysRegListingParamsForm.cadastreObjectSearch.text")); // NOI18N

        configureSRWU.setText(bundle.getString("SysRegListingParamsForm.viewReport.text")); // NOI18N
        configureSRWU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configureSRWUActionPerformed(evt);
            }
        });

        labHeader.setBackground(new java.awt.Color(255, 153, 0));
        labHeader.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labHeader.setForeground(new java.awt.Color(255, 255, 255));
        java.util.ResourceBundle bundle1 = java.util.ResourceBundle.getBundle("org/sola/clients/swing/desktop/cadastre/Bundle"); // NOI18N
        labHeader.setText(bundle1.getString("SysRegWorkUnitForm.labHeader.text")); // NOI18N
        labHeader.setOpaque(true);

        javax.swing.GroupLayout selectionPanelLayout = new javax.swing.GroupLayout(selectionPanel);
        selectionPanel.setLayout(selectionPanelLayout);
        selectionPanelLayout.setHorizontalGroup(
            selectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectionPanelLayout.createSequentialGroup()
                .addComponent(cadastreObjectSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(configureSRWU)
                .addContainerGap())
            .addComponent(labHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        selectionPanelLayout.setVerticalGroup(
            selectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectionPanelLayout.createSequentialGroup()
                .addComponent(labHeader)
                .addGap(10, 10, 10)
                .addGroup(selectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cadastreObjectSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(configureSRWU))
                .addContainerGap(78, Short.MAX_VALUE))
        );

        labNotificationFrom.setText(bundle.getString("SysRegListingParamsForm.labNotificationFrom.text")); // NOI18N

        txtFromDate.setFont(new java.awt.Font("Tahoma", 0, 12));
        txtFromDate.setFormatterFactory(FormattersFactory.getInstance().getDateFormatterFactory());
        txtFromDate.setToolTipText(bundle.getString("SysRegListingParamsForm.txtFromDate.toolTipText")); // NOI18N

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, sysRegWorkUnitBean, org.jdesktop.beansbinding.ELProperty.create("${publicdisplaystartdate}"), txtFromDate, org.jdesktop.beansbinding.BeanProperty.create("value"));
        bindingGroup.addBinding(binding);

        txtFromDate.setComponentOrientation(ComponentOrientation.getOrientation(Locale.getDefault()));
        txtFromDate.setHorizontalAlignment(JTextField.LEADING);

        btnShowCalendarFrom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/common/calendar.png"))); // NOI18N
        btnShowCalendarFrom.setText(bundle.getString("SysRegListingParamsForm.btnShowCalendarFrom.text")); // NOI18N
        btnShowCalendarFrom.setBorder(null);
        btnShowCalendarFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowCalendarFromActionPerformed(evt);
            }
        });

        panelRecorded.setLayout(new java.awt.GridLayout(2, 3, 10, 5));

        jLabel1.setText("Estimated Parcel");
        panelRecorded.add(jLabel1);

        jLabel2.setText("Recorded Parcels");
        panelRecorded.add(jLabel2);

        jLabel3.setText("Recorded Claims");
        panelRecorded.add(jLabel3);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, sysRegWorkUnitBean, org.jdesktop.beansbinding.ELProperty.create("${estimatedparcel}"), txtEstimatedParcel, org.jdesktop.beansbinding.BeanProperty.create("text"), "estimatedGroup");
        bindingGroup.addBinding(binding);

        panelRecorded.add(txtEstimatedParcel);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, sysRegWorkUnitBean, org.jdesktop.beansbinding.ELProperty.create("${recordedparcel}"), txtRecordedParcel, org.jdesktop.beansbinding.BeanProperty.create("text"), "workunitGroup");
        bindingGroup.addBinding(binding);

        panelRecorded.add(txtRecordedParcel);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, sysRegWorkUnitBean, org.jdesktop.beansbinding.ELProperty.create("${recordedclaims}"), txtRecordedClaims, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        panelRecorded.add(txtRecordedClaims);

        panelScanned.setLayout(new java.awt.GridLayout(2, 3, 10, 5));

        jLabel7.setText("Scanned Demarcation");
        panelScanned.add(jLabel7);

        jLabel6.setText("Scanned Claims");
        panelScanned.add(jLabel6);

        jLabel4.setText("Distributed Certificates");
        panelScanned.add(jLabel4);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, sysRegWorkUnitBean, org.jdesktop.beansbinding.ELProperty.create("${scanneddemarcation}"), txtScannedDemarcation, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        panelScanned.add(txtScannedDemarcation);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, sysRegWorkUnitBean, org.jdesktop.beansbinding.ELProperty.create("${scannedclaims}"), txtScannedClaims, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        panelScanned.add(txtScannedClaims);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, sysRegWorkUnitBean, org.jdesktop.beansbinding.ELProperty.create("${distributedcertificates}"), txtDistributedCertificates, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        panelScanned.add(txtDistributedCertificates);

        srwuToolBar.setFloatable(false);
        srwuToolBar.setRollover(true);
        srwuToolBar.setName("srwuToolBar");

        btnSave1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSave1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave1ActionPerformed(evt);
            }
        });
        srwuToolBar.add(btnSave1);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/common/delete_icon.gif"))); // NOI18N
        jButton1.setText("Close");
        jButton1.setFocusable(false);
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        srwuToolBar.add(jButton1);

        labHeader1.setBackground(new java.awt.Color(255, 153, 0));
        labHeader1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labHeader1.setForeground(new java.awt.Color(255, 255, 255));
        labHeader1.setText(bundle1.getString("SysRegWorkUnitForm.labHeader1.text")); // NOI18N
        labHeader1.setOpaque(true);

        javax.swing.GroupLayout srwuPanelLayout = new javax.swing.GroupLayout(srwuPanel);
        srwuPanel.setLayout(srwuPanelLayout);
        srwuPanelLayout.setHorizontalGroup(
            srwuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelScanned, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
            .addGroup(srwuPanelLayout.createSequentialGroup()
                .addGroup(srwuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFromDate, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(labNotificationFrom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnShowCalendarFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(srwuPanelLayout.createSequentialGroup()
                .addGroup(srwuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRecorded, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(srwuToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labHeader1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        srwuPanelLayout.setVerticalGroup(
            srwuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(srwuPanelLayout.createSequentialGroup()
                .addComponent(labHeader1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(srwuToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(panelRecorded, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelScanned, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labNotificationFrom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(srwuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnShowCalendarFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        headerPanel.setTitleText(bundle1.getString("SysRegWorkUnitForm.headerPanel.titleText")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(srwuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(selectionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(41, 41, 41)
                .addComponent(srwuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnShowCalendarFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowCalendarFromActionPerformed
        showCalendar(txtFromDate);
    }//GEN-LAST:event_btnShowCalendarFromActionPerformed

    private void configureSRWUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configureSRWUActionPerformed
        if (cadastreObjectSearch.getSelectedElement() != null) {
            this.firePropertyChange(SELECTED_PARCEL, null,
                    cadastreObjectSearch.getSelectedElement());
            this.location = cadastreObjectSearch.getSelectedElement().toString();
            tmpLocation = (this.location);
            System.out.println("LOCATION     " + tmpLocation);
            createSysRegWorkUnitBean();
            SysRegWorkUnitForm srwu = new SysRegWorkUnitForm(null, true, tmpLocation);
            this.dispose();
            srwu.setVisible(true);
//            setSRWUBean(tmpLocation);   
            
        } else {
            MessageUtility.displayMessage(ClientMessage.CHECK_SELECT_LOCATION);
            return;
        }
    }//GEN-LAST:event_configureSRWUActionPerformed

    private void btnSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave1ActionPerformed
        saveSRWU();
    }//GEN-LAST:event_btnSave1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed
   
      private SysRegWorkUnitBean createSysRegWorkUnitBean() {
        if (this.location != null) {
            this.sysRegWorkUnitBean = getSysRegWorkUnitBean(location);
        } else {
            this.sysRegWorkUnitBean = new SysRegWorkUnitBean();
        }
        
        return this.sysRegWorkUnitBean;
      }
       /**
     * Returns {@link BaUnitBean} by first and last name part.
     */
    private SysRegWorkUnitBean getSysRegWorkUnitBean(String location) {
        SysRegWorkUnitTO workUnitTO = WSManager.getInstance().getCadastreService().getSysRegWorkUnitByAllParts(location);
        return TypeConverters.TransferObjectToBean(workUnitTO, SysRegWorkUnitBean.class, null);
    }
      
      
     /**
     * Returns {@link BaUnitBean} by first and last name part.
     */
    private void saveSRWU(){
    
        
        SolaTask<Boolean, Boolean> t = new SolaTask<Boolean, Boolean>() {

                @Override
                public Boolean doTask() {
                    setMessage(MessageUtility.getLocalizedMessageText(ClientMessage.PROGRESS_MSG_SAVING));
                    return  sysRegWorkUnitBean.saveSysRegWorkUnit(sysRegWorkUnitBean);
                }

                @Override
                public void taskDone() {
                       if (get() != null && get()) {
//                        if (closeOnSave || allowClose) {
//                            close();
//                        } else {
                            MessageUtility.displayMessage(ClientMessage.SYSTEMATIC_REGISTRATION_SRWU_SAVED);
//                            savePartyState();
//                        }
                    }
                }
            };
            TaskManager.getInstance().runTask(t);
    }
    
//    public boolean setSRWUBean(String tmpLocation){
////      sysRegWorkUnitBean  = new SysRegWorkUnitBean();
////      sysRegWorkUnitBean.passParameter(tmpLocation);
////      
//      
//     
//           this.txtEstimatedParcel.setText(sysRegWorkUnitBean.getEstimatedparcel().toString());
//           this.txtRecordedParcel.setText(sysRegWorkUnitBean.getRecordedparcel().toString());
//           this.txtRecordedClaims.setText(sysRegWorkUnitBean.getRecordedclaims().toString());
//           this.txtScannedDemarcation.setText(sysRegWorkUnitBean.getScanneddemarcation().toString());
//           this.txtScannedClaims.setText(sysRegWorkUnitBean.getScannedclaims().toString());
//           this.txtDistributedCertificates.setText(sysRegWorkUnitBean.getDistributedcertificates().toString());
//           this.txtFromDate.setText(sysRegWorkUnitBean.getPublicdisplaystartdate().toString());
////           this.sysRegWorkUnitBean = sysRegWorkUnitBean;
//      return true;
//    }
            
    private void showCalendar(JFormattedTextField dateField) {
        CalendarForm calendar = new CalendarForm(null, true, dateField);
        calendar.setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.sola.clients.swing.common.buttons.BtnSave btnSave1;
    private javax.swing.JButton btnShowCalendarFrom;
    private org.sola.clients.swing.ui.cadastre.LocationSearch cadastreObjectSearch;
    private javax.swing.JButton configureSRWU;
    private org.sola.clients.swing.ui.HeaderPanel headerPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel labHeader;
    private javax.swing.JLabel labHeader1;
    private javax.swing.JLabel labNotificationFrom;
    private javax.swing.JPanel panelRecorded;
    private javax.swing.JPanel panelScanned;
    private javax.swing.JPanel selectionPanel;
    private javax.swing.JPanel srwuPanel;
    private javax.swing.JToolBar srwuToolBar;
    private org.sola.clients.beans.systematicregistration.SysRegWorkUnitBean sysRegWorkUnitBean;
    private javax.swing.JTextField txtDistributedCertificates;
    private javax.swing.JTextField txtEstimatedParcel;
    private javax.swing.JFormattedTextField txtFromDate;
    private javax.swing.JTextField txtRecordedClaims;
    private javax.swing.JTextField txtRecordedParcel;
    private javax.swing.JTextField txtScannedClaims;
    private javax.swing.JTextField txtScannedDemarcation;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
