package com.alpha5.autoaid.dto.response;

public class GetNextRepairResponse {
    private long repairId;
    private String btnStatus;

    public String getBtnStatus() {
        return btnStatus;
    }

    public void setBtnStatus(String btnStatus) {
        this.btnStatus = btnStatus;
    }

    public long getRepairId() {
        return repairId;
    }

    public void setRepairId(long repairId) {
        this.repairId = repairId;
    }
}
