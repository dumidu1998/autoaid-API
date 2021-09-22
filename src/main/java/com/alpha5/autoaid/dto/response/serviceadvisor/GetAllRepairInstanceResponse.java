package com.alpha5.autoaid.dto.response.serviceadvisor;

import com.alpha5.autoaid.enums.RepairStatus;

public class GetAllRepairInstanceResponse {
    private String vin;
    private String vehicleNumber;
    private String repairAddedDate;
    private String repairCompletedDate;
    private RepairStatus status;
    private int Amount;

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

    public String getRepairAddedDate() {
        return repairAddedDate;
    }

    public void setRepairAddedDate(String repairAddedDate) {
        this.repairAddedDate = repairAddedDate;
    }

    public String getRepairCompletedDate() {
        return repairCompletedDate;
    }

    public void setRepairCompletedDate(String repairCompletedDate) {
        this.repairCompletedDate = repairCompletedDate;
    }

    public RepairStatus getStatus() {
        return status;
    }

    public void setStatus(RepairStatus status) {
        this.status = status;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }
}
