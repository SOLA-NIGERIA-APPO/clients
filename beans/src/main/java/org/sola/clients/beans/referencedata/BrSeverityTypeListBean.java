/**
 * ******************************************************************************************
 * Copyright (C) 2012 - Food and Agriculture Organization of the United Nations (FAO).
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice,this list
 *       of conditions and the following disclaimer.
 *    2. Redistributions in binary form must reproduce the above copyright notice,this list
 *       of conditions and the following disclaimer in the documentation and/or other
 *       materials provided with the distribution.
 *    3. Neither the name of FAO nor the names of its contributors may be used to endorse or
 *       promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT
 * SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,STRICT LIABILITY,OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * *********************************************************************************************
 */
package org.sola.clients.beans.referencedata;

import java.util.ArrayList;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;
import org.sola.clients.beans.AbstractBindingListBean;
import org.sola.clients.beans.cache.CacheManager;

/**
 * Holds list of {@link BrSeverityTypeBean} objects.
 */
public class BrSeverityTypeListBean extends AbstractBindingListBean {
    public static final String SELECTED_BR_SEVERITY_TYPE_PROPERTY = "selectedBrSeverityType";
    private ObservableList<BrSeverityTypeBean> brSeverityTypes;
    private BrSeverityTypeBean selectedBrSeverityType;

    /** Default constructor. */
    public BrSeverityTypeListBean(){
        this(false);
    }
    
    /** 
     * Creates object instance.
     * @param createDummy Indicates whether to add empty object on the list.
     */
    public BrSeverityTypeListBean(boolean createDummy) {
        super();
        loadList(createDummy);
    }
    
    /** 
     * Loads list of {@link BrSeverityTypeBean}.
     * @param createDummy Indicates whether to add empty object on the list.
     */
    public final void loadList(boolean createDummy) {
        if (brSeverityTypes == null) {
            brSeverityTypes = ObservableCollections.observableList(new ArrayList<BrSeverityTypeBean>());
        }
        loadCodeList(BrSeverityTypeBean.class, brSeverityTypes, 
                CacheManager.getBrSeverityTypes(), createDummy);
    }

    public ObservableList<BrSeverityTypeBean> getBrSeverityTypes() {
        return brSeverityTypes;
    }

    public BrSeverityTypeBean getSelectedBrSeverityType() {
        return selectedBrSeverityType;
    }

    public void setSelectedBrSeverityType(BrSeverityTypeBean selectedBrSeverityType) {
        BrSeverityTypeBean oldValue = this.selectedBrSeverityType;
        this.selectedBrSeverityType = selectedBrSeverityType;
        propertySupport.firePropertyChange(SELECTED_BR_SEVERITY_TYPE_PROPERTY, oldValue, this.selectedBrSeverityType);
    }
}
