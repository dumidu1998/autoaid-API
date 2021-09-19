package com.alpha5.autoaid.dto.response;

public class ChartResponse extends ExpenseResponse {
    private String vehicleNo;

    public ChartResponse(float total, float totalMonth, float avg, float avgRep, int activeRe,String vno) {
        super(total, totalMonth, avg, avgRep, activeRe);
        this.vehicleNo=vno;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }
}
