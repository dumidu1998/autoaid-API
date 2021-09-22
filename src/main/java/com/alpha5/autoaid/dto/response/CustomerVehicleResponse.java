package com.alpha5.autoaid.dto.response;

import com.alpha5.autoaid.model.Vehicle;

public class CustomerVehicleResponse {

    private Vehicle vehicle;
    private String nextService;

    public CustomerVehicleResponse(Vehicle vehicle, String nextService) {
        this.vehicle = vehicle;
        this.nextService = nextService;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getNextService() {
        return nextService;
    }

    public void setNextService(String nextService) {
        this.nextService = nextService;
    }
}
