package com.alpha5.autoaid.dto.request;

import java.util.Date;

public class NewAppointmentRequest {
    private Date date;
    private String time;
    private long vehicleId;
    private String vehicleName;
    private long serviceAdvisorId;
    private long customerId;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public Long getServiceAdvisorId() {
        return serviceAdvisorId;
    }

    public void setServiceAdvisorId(Long serviceAdvisorId) {
        this.serviceAdvisorId = serviceAdvisorId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
