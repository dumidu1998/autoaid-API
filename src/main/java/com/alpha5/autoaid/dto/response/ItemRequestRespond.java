package com.alpha5.autoaid.dto.response;

import java.math.BigDecimal;

public class ItemRequestRespond {

    private long requestId;
    private int quantity;
    private String itemName;
    private long repair;
    private String vehicleNumber;


    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getRepair() {
        return repair;
    }

    public void setRepair(long repair) {
        this.repair = repair;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
