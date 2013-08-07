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
/**
 *
 * LAA Addition thoriso
 */
package org.sola.clients.beans.administrative;

import org.sola.webservices.transferobjects.EntityAction;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.validation.Valid;
import org.sola.clients.beans.AbstractTransactionedBean;
import org.sola.clients.beans.cache.CacheManager;
import org.sola.clients.beans.converters.TypeConverters;
import org.sola.services.boundary.wsclients.WSManager;
import org.sola.webservices.transferobjects.administrative.DisputeTO;
import org.sola.clients.beans.referencedata.DisputeCategoryBean;
import org.sola.clients.beans.referencedata.DisputeTypeBean;
import org.sola.clients.beans.cadastre.CadastreObjectBean;
import org.sola.clients.beans.controls.SolaList;
import org.jdesktop.observablecollections.ObservableList;
import org.sola.clients.beans.controls.SolaObservableList;

/**
 *
 *
 */
public class DisputeBean extends AbstractTransactionedBean {

    public static final String DISP_ID_PROPERTY = "id";
    public static final String NR_PROPERTY = "nr";
    public static final String LODGEMENT_DATE_PROPERTY = "lodgementDate";
    public static final String COMPLETION_DATE_PROPERTY = "completiondate";
    public static final String DISPUTE_CATEGORY_PROPERTY = "disputeCategory";
    public static final String DISPUTE_TYPE_PROPERTY = "disputeType";
    public static final String STATUS_CODE_PROPERTY = "statusCode";
    public static final String LEASE_NUMBER_PROPERTY = "leaseNumber";
    public static final String PLOT_LOCATION_PROPERTY = "plotLocation";
    public static final String PLOT_NUMBER_PROPERTY = "plotNumber";
    public static final String CASE_TYPE_PROPERTY = "caseType";
    public static final String PRIMARY_RESPONDENT_PROPERTY = "primaryRespondent";
    public static final String ACTION_REQUIRED_PROPERTY = "actionRequired";
    public static final String SELECTED_CATEGORY_PROPERTY = "selectedCategory";
    public static final String SELECTED_TYPE_PROPERTY = "selectedType";
    public static final String USER_ID_PROPERTY = "userid";
    public static final String APPLICATION_ID_PROPERTY = "applicationId";
    public static final String SERVICE_ID_PROPERTY = "serviceId";
    public static final String BEAN_PROPERTY = "bean";
    public static final String SELECTED_COMMENTS_PROPERTY = "selectedComments";
    public static final String SELECTED_PARTY_PROPERTY = "selectedParty";
    public static final String DISPUTE_PROPERTY = "dispute";
    private String id;
    private String applicationId;
    private String serviceId;
    private String nr;
    private Date lodgementDate;
    private Date completionDate;
    private DisputeCategoryBean disputeCategoryCode;
    private DisputeTypeBean disputeTypeCode;
    private String statusCode;
    private String leaseNumber;
    private String plotLocation;
    private String plotNumber;
    private String caseType;
    private boolean primaryRespondent;
    private String actionRequired;
    private String userId;
    private DisputeBean bean;
    private SolaList<DisputesCommentsBean> disputeCommentsList;
    private SolaList<DisputePartyBean> disputePartyList;
    private SolaList<DisputePartyBean> disputePartyByDispute;
    private transient DisputesCommentsBean selectedComment;
    private transient DisputePartyBean selectedParty;

    public DisputeBean() {
        super();
//        disputeCommentsList = new SolaList<DisputesCommentsBean>();
        disputePartyByDispute = new SolaList<DisputePartyBean>();
        disputePartyList = new SolaList<DisputePartyBean>();
       
        //selectedComment = new DisputesCommentsBean();
        //selectedParty = new DisputePartyBean(); 
        disputeCommentsList = new SolaList();

    }

    public void clean() {
        this.setPlotNumber(null);
        this.setLodgementDate(null);
        this.setCompletiondate(null);
        this.setDisputeCategory(null);
        this.setDisputeType(null);
        this.setStatusCode(null);
        this.setNr(null);
        this.setLeaseNumber(null);
        this.setPlotLocation(null);
        this.setId(null);
        this.setDisputeCommentsList(null);
        this.setDisputePartyList(null);


    }

    public void updateCheckList(List<DisputesCommentsBean> commentsList) {
        if (commentsList != null) {

            for (Iterator<DisputesCommentsBean> it = commentsList.iterator(); it.hasNext();) {
                DisputesCommentsBean disputesCommentsBean = it.next();

            }
        }
    }

    public SolaList<DisputesCommentsBean> getDisputeCommentsList() {
//        if (disputeCommentsList == null) {
//            disputeCommentsList = new SolaList();
//        }
        return disputeCommentsList;
    }

    @Valid
    public ObservableList<DisputesCommentsBean> getFilteredDisputeCommentsList() {
//        if (disputeCommentsList == null) {
//            disputeCommentsList = new SolaList();
//        }
        return disputeCommentsList.getFilteredList();
    }

    public void setDisputeCommentsList(SolaList<DisputesCommentsBean> disputeCommentsList) {
        this.disputeCommentsList = disputeCommentsList;
    }

    public void setSelectedComment(DisputesCommentsBean selectedComment) {
        //DisputesCommentsBean oldValue = this.selectedComment;
        this.selectedComment = selectedComment;
        propertySupport.firePropertyChange(SELECTED_COMMENTS_PROPERTY, null, this.selectedComment);
    }

    public DisputesCommentsBean getSelectedComment() {
        return selectedComment;
    }

    public void addDisputeComment(DisputesCommentsBean disputeComment) {
        if (getDisputeCommentsList() != null && disputeComment != null
                && disputeComment.getEntityAction() != EntityAction.DELETE
                && disputeComment.getEntityAction() != EntityAction.DISASSOCIATE) {

            for (DisputesCommentsBean co : getDisputeCommentsList()) {
                if (co.getId() != null && disputeComment.getId() != null && co.getId().equals(disputeComment.getId())) {
                    if (co.getEntityAction() == EntityAction.DELETE || co.getEntityAction() == EntityAction.DISASSOCIATE) {
                        co.setEntityAction(null);
                    }
                    return;
                }
            }

            getDisputeCommentsList().addAsNew(disputeComment);
        }
    }

    public void removeSelectedComment() {
        if (selectedComment != null && disputeCommentsList != null) {
            disputeCommentsList.safeRemove(selectedComment, EntityAction.DISASSOCIATE);
        }
    }

    public SolaList<DisputePartyBean> getDisputePartyList() {
        if (disputePartyList == null) {
            disputePartyList = new SolaList();
        }
        return disputePartyList;
    }

    public ObservableList<DisputePartyBean> getFilteredDisputePartyList() {
        if (disputePartyList == null) {
            disputePartyList = new SolaList();
        }
        
         System.out.println("disputePartyList  "+disputePartyList);
        
        return disputePartyList.getFilteredList();
    }

    public void setDisputePartyList(SolaList<DisputePartyBean> disputePartyList) {
        this.disputePartyList = disputePartyList;
    }

    public void setSelectedParty(DisputePartyBean selectedParty) {
        this.selectedParty = selectedParty;
        propertySupport.firePropertyChange(SELECTED_PARTY_PROPERTY, null, this.selectedParty);
    }

    public DisputePartyBean getDisputeParty() {
        return selectedParty;
    }

    public void addDisputeParty(DisputePartyBean disputeParty) {
        if (getDisputePartyList() != null && disputeParty != null
                && disputeParty.getEntityAction() != EntityAction.DELETE
                && disputeParty.getEntityAction() != EntityAction.DISASSOCIATE) {
            if (getDisputePartyList().size() < 2) {
                for (DisputePartyBean co : getDisputePartyList()) {
                    System.out.println("co.getId()   " + co.getId());
                    System.out.println("disputeParty.getId() " + disputeParty.getId());
                    System.out.println("SIZE   " + getDisputePartyList().size());

                    if (co.getId() != null && disputeParty.getId() != null && co.getId().equals(disputeParty.getId())) {
                        if (co.getEntityAction() == EntityAction.DELETE || co.getEntityAction() == EntityAction.DISASSOCIATE) {
                            co.setEntityAction(null);
                        }
                        return;
                    }

                    if (co.getPartyId().equals(disputeParty.getPartyId())) {
//                    MESSAGE TOBEADDED
                        System.out.println("PAOLA 1 " + co.getPartyId());
                        System.out.println("PAOLA 2 " + disputeParty.getPartyId());
                        return;
                    }

                }
                System.out.println("disputeParty " + disputeParty.getPartyName(disputeParty.getId()));
                getDisputePartyList().addAsNew(disputeParty);
            } else {
//                MESSAGE TOBEADDED
                        System.out.println("PAOLA 3 " + getDisputePartyList().size());
                        return;
                
            }
        }
    }

    public void removeSelectedParty() {
        if (selectedParty != null && disputePartyList != null) {
            disputePartyList.safeRemove(selectedParty, EntityAction.DISASSOCIATE);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String value) {
        String old = id;
        id = value;
        propertySupport.firePropertyChange(DISP_ID_PROPERTY, old, id);

    }

    public String getPlotNumber() {
        return plotNumber;
    }

    public void setPlotNumber(String value) {
        String old = plotNumber;
        plotNumber = value;
        propertySupport.firePropertyChange(PLOT_NUMBER_PROPERTY, old, plotNumber);

    }

    public Date getCompletiondate() {
        return completionDate;
    }

    public void setCompletiondate(Date value) {
        Date old = completionDate;
        completionDate = value;
        propertySupport.firePropertyChange(COMPLETION_DATE_PROPERTY, old, completionDate);
    }

    public String getDisputeCategoryCode() {
        return getDisputeCategory().getCode();
    }

    public void setDisputeCategoryCode(String value) {
        String oldValue = getDisputeCategory().getCode();
        setDisputeCategory(CacheManager.getBeanByCode(CacheManager.getDisputeCategory(), value));
        propertySupport.firePropertyChange(DISPUTE_CATEGORY_PROPERTY, oldValue, value);
    }

    public DisputeCategoryBean getDisputeCategory() {
        if (disputeCategoryCode == null) {
            disputeCategoryCode = new DisputeCategoryBean();
        }
        return disputeCategoryCode;
    }

    public void setDisputeCategory(DisputeCategoryBean disputeCategory) {
        this.setJointRefDataBean(getDisputeCategory(), disputeCategory, DISPUTE_CATEGORY_PROPERTY);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        String oldValue = this.userId;
        this.userId = userId;
        propertySupport.firePropertyChange(USER_ID_PROPERTY, oldValue, this.userId);
    }

    public String getDisputeTypeCode() {
        if (disputeTypeCode != null) {
            return disputeTypeCode.getCode();
        } else {
            return null;
        }
    }

    public void setDisputeTypeCode(String disputeTypeCode) {
        String oldValue = null;
        if (this.disputeTypeCode != null) {
            oldValue = this.disputeTypeCode.getCode();
            return;
        }

        setDisputeType(CacheManager.getBeanByCode(
                CacheManager.getDisputeType(), disputeTypeCode));
        propertySupport.firePropertyChange(DISPUTE_TYPE_PROPERTY, oldValue, disputeTypeCode);
    }

    public DisputeTypeBean getDisputeType() {
        if (this.disputeTypeCode == null) {
            this.disputeTypeCode = new DisputeTypeBean();
        }
        return disputeTypeCode;
    }

    public void setDisputeType(DisputeTypeBean disputeType) {
        if (this.disputeTypeCode == null) {
            this.disputeTypeCode = new DisputeTypeBean();
        }
        this.setJointRefDataBean(this.disputeTypeCode, disputeType, DISPUTE_TYPE_PROPERTY);
    }

    public Date getLodgementDate() {
        return lodgementDate;
    }

    public void setLodgementDate(Date value) {
        Date old = lodgementDate;
        lodgementDate = value;
        propertySupport.firePropertyChange(LODGEMENT_DATE_PROPERTY, old, lodgementDate);
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String value) {
        String old = nr;
        nr = value;
        propertySupport.firePropertyChange(NR_PROPERTY, old, nr);
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String value) {
        String old = caseType;
        caseType = value;
        propertySupport.firePropertyChange(CASE_TYPE_PROPERTY, old, caseType);
    }

    public String getActionRequired() {
        return actionRequired;
    }

    public void setActionRequired(String value) {
        String old = actionRequired;
        actionRequired = value;
        propertySupport.firePropertyChange(ACTION_REQUIRED_PROPERTY, old, actionRequired);
    }

    public boolean isPrimaryRespondent() {
        return primaryRespondent;
    }

    public void setPrimaryRespondent(boolean value) {
        boolean old = primaryRespondent;
        primaryRespondent = value;
        propertySupport.firePropertyChange(PRIMARY_RESPONDENT_PROPERTY, old, primaryRespondent);
    }

    public String getPlotLocation() {
        return plotLocation;
    }

    public void setPlotLocation(String value) {
        String old = plotLocation;
        plotLocation = value;
        propertySupport.firePropertyChange(PLOT_LOCATION_PROPERTY, old, plotLocation);
    }

    public String getLeaseNumber() {
        return leaseNumber;
    }

    public void setLeaseNumber(String value) {
        String old = leaseNumber;
        leaseNumber = value;
        propertySupport.firePropertyChange(LEASE_NUMBER_PROPERTY, old, leaseNumber);
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String value) {
        String old = statusCode;
        statusCode = value;
        propertySupport.firePropertyChange(STATUS_CODE_PROPERTY, old, statusCode);
    }

    public void addChosenPlot(CadastreObjectBean cadastreObjectBean) {
        if (cadastreObjectBean != null) {
            setPlotNumber(cadastreObjectBean.getNameFirstpart() + "-" + cadastreObjectBean.getNameLastpart());
        }
    }

    public void assignDispute(DisputeSearchResultBean disputeSearchResultBean) {
        if (disputeSearchResultBean.getId() != null) {
            //String disputeId = disputeSearchResultBean.getId();
            DisputeTO dispute = TypeConverters.BeanToTrasferObject(disputeSearchResultBean, DisputeTO.class);
            DisputeBean disputeBean = TypeConverters.TransferObjectToBean(dispute, DisputeBean.class, this);
            setBean(disputeBean);
        }
    }

    public DisputeBean getBean() {
        return bean;
    }

    public void setBean(DisputeBean value) {
        bean = value;
        propertySupport.firePropertyChange(BEAN_PROPERTY, null, value);
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public void setDisputeCategoryCode(DisputeCategoryBean disputeCategoryCode) {
        this.disputeCategoryCode = disputeCategoryCode;
    }

    public void setDisputeTypeCode(DisputeTypeBean disputeTypeCode) {
        this.disputeTypeCode = disputeTypeCode;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public boolean createDispute() {
        DisputeTO dispute = TypeConverters.BeanToTrasferObject(this, DisputeTO.class);
        dispute = WSManager.getInstance().getAdministrative().createDispute(dispute);
        TypeConverters.TransferObjectToBean(dispute, DisputeBean.class, this);
        return true;
    }

    public boolean saveDispute() {
        DisputeTO dispute = TypeConverters.BeanToTrasferObject(this, DisputeTO.class);
        dispute = WSManager.getInstance().getAdministrative().saveDispute(dispute);
        TypeConverters.TransferObjectToBean(dispute, DisputeBean.class, this);
        return true;
    }

    /**
     * Returns Dispute by ID.
     *
     * @param disputeId The ID of Dispute to return.
     */
    public static DisputeBean getDisputeById(String disputeId) {
        return TypeConverters.TransferObjectToBean(
                WSManager.getInstance().getAdministrative().getDisputeById(disputeId),
                DisputeBean.class, null);
    }

    /**
     * Returns Dispute by NR.
     *
     * @param disputeNr The Nr of Dispute to return.
     */
    public static DisputeBean getDisputeByNr(String disputeNr) {
        return TypeConverters.TransferObjectToBean(
                WSManager.getInstance().getAdministrative().getDisputeByNr(disputeNr),
                DisputeBean.class, null);
    }
    
    
     /**
     * Returns Dispute by NR.
     *
     * @param disputeNr The Parties of DisputeParty to return.
     */
    public static List<DisputePartyBean> getDisputePartyByDispute(String disputeNr) {
        return TypeConverters.TransferObjectListToBeanList(WSManager.getInstance().getAdministrative().getDisputePartyByDispute(disputeNr),
                DisputePartyBean.class, null);
    }

    /**
     * Returns Dispute by User.
     *
     * @param disputeUser The User of Dispute to return.
     */
    public static DisputeBean getDisputeByUser(String user) {
        return TypeConverters.TransferObjectToBean(
                WSManager.getInstance().getAdministrative().getDisputeByUser(user),
                DisputeBean.class, null);
    }

    /**
     * Returns Dispute by Service.
     *
     * @param disputeService The Service of Dispute to return.
     */
    public static DisputeBean getDisputeByService(String service) {
        return TypeConverters.TransferObjectToBean(
                WSManager.getInstance().getAdministrative().getDisputeByService(service),
                DisputeBean.class, null);
    }

    /**
     * Returns client bean, related to lodged user.
     */
    public static DisputeBean getDispute(String disputeId) {
        if (disputeId == null || disputeId.length() < 1) {
            return null;
        }
        DisputeTO disputeTO = WSManager.getInstance().getAdministrative().getDispute(disputeId);
        return TypeConverters.TransferObjectToBean(disputeTO, DisputeBean.class, null);
    }
}
