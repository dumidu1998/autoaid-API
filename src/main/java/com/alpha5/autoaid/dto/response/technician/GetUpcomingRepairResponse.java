package com.alpha5.autoaid.dto.response.technician;

public class GetUpcomingRepairResponse {
    private long repairId;
    private String vin;
    private String vehicleNumber;
    private boolean btnAct;

    public boolean isBtnAct() {
        return btnAct;
    }

    public void setBtnAct(boolean btnAct) {
        this.btnAct = btnAct;
    }

    public long getRepairId() {
        return repairId;
    }

    public void setRepairId(long repairId) {
        this.repairId = repairId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
