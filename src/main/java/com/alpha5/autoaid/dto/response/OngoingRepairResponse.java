package com.alpha5.autoaid.dto.response;

import com.alpha5.autoaid.enums.RepairStatus;

public class OngoingRepairResponse {
    private String vehicleNumber;
    private String sectionName;
    private RepairStatus status;

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public RepairStatus getStatus() {
        return status;
    }

    public void setStatus(RepairStatus status) {
        this.status = status;
    }
}
