package com.alpha5.autoaid.dto.response;

public class ExpenseResponse {
    private float total;
    private float totalMonth;
    private  float avg;
    private float avgRep;
    private int activeRe;

    public ExpenseResponse(float total, float totalMonth, float avg, float avgRep, int activeRe) {
        this.total = total;
        this.totalMonth = totalMonth;
        this.avg = avg;
        this.avgRep = avgRep;
        this.activeRe = activeRe;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getTotalMonth() {
        return totalMonth;
    }

    public void setTotalMonth(float totalMonth) {
        this.totalMonth = totalMonth;
    }

    public float getAvg() {
        return avg;
    }

    public void setAvg(float avg) {
        this.avg = avg;
    }

    public float getAvgRep() {
        return avgRep;
    }

    public void setAvgRep(float avgRep) {
        this.avgRep = avgRep;
    }

    public int getActiveRe() {
        return activeRe;
    }

    public void setActiveRe(int activeRe) {
        this.activeRe = activeRe;
    }
}
