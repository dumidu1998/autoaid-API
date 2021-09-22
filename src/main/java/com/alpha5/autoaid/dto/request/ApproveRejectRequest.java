package com.alpha5.autoaid.dto.request;

import com.alpha5.autoaid.enums.SpecialItemRequestStatus;

import java.math.BigDecimal;

public class ApproveRejectRequest {
    private long requestId;
    private SpecialItemRequestStatus status;
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public SpecialItemRequestStatus getStatus() {
        return status;
    }

    public void setStatus(SpecialItemRequestStatus status) {
        this.status = status;
    }
}
