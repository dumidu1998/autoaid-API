package com.alpha5.autoaid.dto.response.technician;

import com.alpha5.autoaid.enums.ServiceEntryStatus;

public class GetEntryListResponse {
    private long subCatId;
    private String subCatName;
    private int estimatedTime;
    private String description;
    private ServiceEntryStatus serviceEntryStatus;

    public ServiceEntryStatus getServiceEntryStatus() {
        return serviceEntryStatus;
    }

    public void setServiceEntryStatus(ServiceEntryStatus serviceEntryStatus) {
        this.serviceEntryStatus = serviceEntryStatus;
    }

    public long getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(long subCatId) {
        this.subCatId = subCatId;
    }

    public String getSubCatName() {
        return subCatName;
    }

    public void setSubCatName(String subCatName) {
        this.subCatName = subCatName;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
