package com.alpha5.autoaid.dto.request;

import java.math.BigDecimal;

public class AddInvoiceDataRequest {

    private long repairId;
    private BigDecimal amount;

    public long getRepairId() {
        return repairId;
    }

    public void setRepairId(long repairId) {
        this.repairId = repairId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
