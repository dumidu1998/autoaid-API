package com.alpha5.autoaid.dto.request;

import com.alpha5.autoaid.enums.PaymentType;

public class AddNewRepairsRequest {
    private PaymentType payment_type;
    private String userName;
    private String vin;

    public PaymentType getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(PaymentType payment_type) {
        this.payment_type = payment_type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
