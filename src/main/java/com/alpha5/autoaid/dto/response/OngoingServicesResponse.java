package com.alpha5.autoaid.dto.response;

import java.util.List;

public class OngoingServicesResponse {
    private long repairId;
    private String vehicleNumber;
    private int estTime;
    private List<String> queued;
    private List<String> ongoing;
    private List<String> completed;

    public OngoingServicesResponse(long repairId, String vehicleNumber, int estTime, List<String> queued, List<String> ongoing, List<String> completed) {
        this.repairId = repairId;
        this.vehicleNumber = vehicleNumber;
        this.estTime = estTime;
        this.queued = queued;
        this.ongoing = ongoing;
        this.completed = completed;
    }

    public int getEstTime() {
        return estTime;
    }

    public void setEstTime(int estTime) {
        this.estTime = estTime;
    }

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

    public List<String> getQueued() {
        return queued;
    }

    public void setQueued(List<String> queued) {
        this.queued = queued;
    }

    public List<String> getOngoing() {
        return ongoing;
    }

    public void setOngoing(List<String> ongoing) {
        this.ongoing = ongoing;
    }

    public List<String> getCompleted() {
        return completed;
    }

    public void setCompleted(List<String> completed) {
        this.completed = completed;
    }
}
