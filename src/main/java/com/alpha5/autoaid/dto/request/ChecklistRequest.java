package com.alpha5.autoaid.dto.request;

import com.alpha5.autoaid.enums.RepairType;

public class ChecklistRequest {
    private long repairId;
    private String fbdocid;
    private RepairType repairType;
    private int millage;

    public long getRepairId() {
        return repairId;
    }

    public void setRepairId(long repairId) {
        this.repairId = repairId;
    }

    public String getFbdocid() {
        return fbdocid;
    }

    public void setFbdocid(String fbdocid) {
        this.fbdocid = fbdocid;
    }

    public RepairType getRepairType() {
        return repairType;
    }

    public void setRepairType(RepairType repairType) {
        this.repairType = repairType;
    }

    public int getMillage() {
        return millage;
    }

    public void setMillage(int millage) {
        this.millage = millage;
    }
}
