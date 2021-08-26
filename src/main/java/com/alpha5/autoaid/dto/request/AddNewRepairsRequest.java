package com.alpha5.autoaid.dto.request;

import com.alpha5.autoaid.enums.PaymentType;

public class AddNewRepairsRequest {
    private PaymentType paymentType;
    private long userId;
    private String vin;

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userName) {
        this.userId = userName;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
