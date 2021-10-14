package com.alpha5.autoaid.dto.response;

import java.math.BigDecimal;

public class ServiceList {
    private String repairName;
    private BigDecimal price;
    private int newer;

    public ServiceList(String repairName, BigDecimal price) {
        this.repairName = repairName;
        this.price = price;
    }

    public String getRepairName() {
        return repairName;
    }

    public void setRepairName(String repairName) {
        this.repairName = repairName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
