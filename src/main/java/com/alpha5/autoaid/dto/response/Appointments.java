package com.alpha5.autoaid.dto.response;

import java.util.Date;

public class Appointments {
    private Date appointmentDate;
    private String appointmentTime;
    private String vehicleNumber;
    private String serviceAdvisorName;

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getServiceAdvisorName() {
        return serviceAdvisorName;
    }

    public void setServiceAdvisorName(String serviceAdvisorName) {
        this.serviceAdvisorName = serviceAdvisorName;
    }
}
