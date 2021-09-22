package com.alpha5.autoaid.dto.response;

import java.util.Date;

public class CashierRepairDetailsRespond {
    private long repairId;
    private String vehicleNumber;
    private String contactNo;
    private String Name;
    private String repairCompletedDate;


    public long getRepairId() {
        return repairId;
    }

    public void setRepairId(long repairId) {
        this.repairId = repairId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRepairCompletedDate() {
        return repairCompletedDate;
    }

    public void setRepairCompletedDate(String repairCompletedDate) {
        this.repairCompletedDate = repairCompletedDate;
    }
}
