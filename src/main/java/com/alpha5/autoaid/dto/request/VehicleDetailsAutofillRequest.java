package com.alpha5.autoaid.dto.request;

public class VehicleDetailsAutofillRequest {
    //use vin since if every vehicle has VIN and every vehicle not registered

    private String vin;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
