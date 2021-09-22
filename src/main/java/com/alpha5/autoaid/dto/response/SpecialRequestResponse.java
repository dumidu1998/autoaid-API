package com.alpha5.autoaid.dto.response;

public class SpecialRequestResponse {
    private long requestId;
    private String vehicleNo;
    private String techieName;
    private String sectionName;
    private String itemName;
    private int qty;

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getTechieName() {
        return techieName;
    }

    public void setTechieName(String techieName) {
        this.techieName = techieName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
