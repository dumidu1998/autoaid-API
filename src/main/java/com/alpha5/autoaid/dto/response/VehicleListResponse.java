package com.alpha5.autoaid.dto.response;

// get vehicles of customers and show vehicle number list
public class VehicleListResponse {
    private String vehicleNumber;
    private String vin;

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
