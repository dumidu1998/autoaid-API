package com.alpha5.autoaid.dto.request;

public class AddAppointmentV extends AddAppointment{

    private String vNo;
    private long userid;

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getvNo() {
        return vNo;
    }

    public void setvNo(String vNo) {
        this.vNo = vNo;
    }
}
