package com.alpha5.autoaid.dto.response;

public class VehicleDetailsAutofillResponse {
    private int vehiceId;
    private String chassisNo;
    private String enginNo;
    private String model;
    private String make;

    public int getVehiceId() {
        return vehiceId;
    }

    public void setVehiceId(int vehiceId) {
        this.vehiceId = vehiceId;
    }

    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    public String getEnginNo() {
        return enginNo;
    }

    public void setEnginNo(String enginNo) {
        this.enginNo = enginNo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }
}
