package com.alpha5.autoaid.dto.response;

import java.util.Date;

public class NewAppointmentRespond {
    private Date date;
    private  String time;

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
}
