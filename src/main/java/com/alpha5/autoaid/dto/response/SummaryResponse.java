package com.alpha5.autoaid.dto.response;

import java.math.BigDecimal;

public class SummaryResponse {
    private int newUsers;
    private double usergrow;
    private int newRepairs;
    private double repairgrow;
    private BigDecimal sales;
    private int emps;

    public SummaryResponse(int newUsers, double usergrow, int newRepairs, double repairgrow, BigDecimal sales, int emps) {


        this.newUsers = newUsers;
        this.usergrow = usergrow;
        this.newRepairs = newRepairs;
        this.repairgrow = repairgrow;
        this.sales = sales;
        this.emps = emps;
    }

    public int getNewUsers() {
        return newUsers;
    }

    public void setNewUsers(int newUsers) {
        this.newUsers = newUsers;
    }

    public double getUsergrow() {
        return usergrow;
    }

    public void setUsergrow(double usergrow) {
        this.usergrow = usergrow;
    }

    public int getNewRepairs() {
        return newRepairs;
    }

    public void setNewRepairs(int newRepairs) {
        this.newRepairs = newRepairs;
    }

    public double getRepairgrow() {
        return repairgrow;
    }

    public void setRepairgrow(double repairgrow) {
        this.repairgrow = repairgrow;
    }

    public BigDecimal getSales() {
        return sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }

    public int getEmps() {
        return emps;
    }

    public void setEmps(int emps) {
        this.emps = emps;
    }
}
