/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.clients.swing.bulkoperations.beans;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import org.sola.clients.beans.cache.CacheManager;
import org.sola.clients.beans.digitalarchive.DocumentBean;
import org.sola.clients.beans.source.SourceBean;
import org.sola.clients.beans.validation.ValidationResultBean;
import org.sola.common.messaging.ClientMessage;
import org.sola.common.messaging.MessageUtility;

/**
 * It is used to upload documents (sources is called within the framework of Sola)
 * from a given folder to the source schema in the database.
 * 
 * @author Elton Manoku
 */
public class SourceBulkMoveBean extends AbstractBulkMoveBean{

    private File baseFolder;
    private List<SourceBean> sourceList = null;
    private List<String> allowedExtensions = new ArrayList<String>();

    /**
     * The constructor initializes also the filter for files that will be considered
     * for upload.
     */
    public SourceBulkMoveBean() {
        allowedExtensions.add(".tiff");
        allowedExtensions.add(".tif");
        allowedExtensions.add(".pdf");
        allowedExtensions.add(".jpg");
    }

    @Override
    public TransactionBulkOperationSource getTransaction() {
        return (TransactionBulkOperationSource) super.getTransaction();
    }

    /**
     * Gets the folder where the documents are found.
     * @return 
     */
    public File getBaseFolder() {
        return baseFolder;
    }

    public void setBaseFolder(File baseFolder) {
        this.baseFolder = baseFolder;
    }

    /**
     * The list of allowed extensions.
     * 
     * @return 
     */
    public List<String> getAllowedExtensions() {
        return allowedExtensions;
    }

    @Override
    public void sendToServer() {
        getValidationResults().clear();
        setTransaction(new TransactionBulkOperationSource());
        getTransaction().setSourceList(getSources());
        getValidationResults().addAll(getTransaction().save());
    }

    private FileFilter getSourceFileFilter() {
        return new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                if (pathname.isDirectory()) {
                    return false;
                }
                for (String allowedExtension : getAllowedExtensions()) {
                    if (pathname.getName().toLowerCase().endsWith(allowedExtension)) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    private List<SourceBean> getSources() {
        sourceList = new ArrayList<SourceBean>();
        for (File subFolderObj : getBaseFolder().listFiles()) {
            if (!subFolderObj.isDirectory()) {
                continue;
            }
            treatSourceTypeFolder(subFolderObj);
        }
        return sourceList;
    }

    private void treatSourceTypeFolder(File sourceTypeFolder) {
        String sourceType = sourceTypeFolder.getName();
        if (CacheManager.getBeanByCode(CacheManager.getSourceTypes(), sourceType) == null) {
            ValidationResultBean validationBean = new ValidationResultBean();
            validationBean.setSeverity(ValidationResultBean.SEVERITY_WARNING);
            Object[] params = new Object[1];
            params[0] = sourceType;
            String feedback = MessageUtility.getLocalizedMessage(
                    ClientMessage.BULK_OPERATIONS_LOAD_SOURCE_TYPE_NOT_FOUND, params).getMessage();
            validationBean.setFeedback(feedback);
            validationBean.setSuccessful(false);
            getValidationResults().add(validationBean);
            return;
        }
        for (File sourceFile : sourceTypeFolder.listFiles(getSourceFileFilter())) {
            SourceBean sourceBean = new SourceBean();
            sourceBean.setTypeCode(sourceType);
            sourceBean.setReferenceNr(getReferenceNrFromFileName(sourceFile.getName()));
            DocumentBean document = DocumentBean.createDocumentFromLocalFile(sourceFile);
            sourceBean.setArchiveDocument(document);
            sourceList.add(sourceBean);
        }
    }

    private String getReferenceNrFromFileName(String fileName) {
        String name = fileName;
        int pos = name.lastIndexOf(".");
        if (pos > 0) {
            name = name.substring(0, pos);
        }
        return name;
    }
}
