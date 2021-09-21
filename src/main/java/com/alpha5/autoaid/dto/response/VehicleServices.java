package com.alpha5.autoaid.dto.response;

public class VehicleServices {
    private long repairId;
    private String date;

    public VehicleServices(long repairId, String date) {
        this.repairId = repairId;
        this.date = date;
    }

    public long getRepairId() {
        return repairId;
    }

    public void setRepairId(long repairId) {
        this.repairId = repairId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
