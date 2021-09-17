package com.alpha5.autoaid.dto.request;

public class TechnicianRepairAcceptanceRequest {
    private long repairId;
    private String sectionName;

    public long getRepairId() {
        return repairId;
    }

    public void setRepairId(long repairId) {
        this.repairId = repairId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
}
