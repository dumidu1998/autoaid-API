package com.alpha5.autoaid.dto.response;

import java.math.BigDecimal;

public class TransactionResponse {

    private String vehicleNo;
    private BigDecimal amount;


    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
