package com.alpha5.autoaid.dto.request;

public class AddNewServiceEntryRequest {
     private long userId;
     private long repairId;
     private ServiceEntryInstance[] serviceEntryInstances;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRepairId() {
        return repairId;
    }

    public void setRepairId(long repairId) {
        this.repairId = repairId;
    }

    public ServiceEntryInstance[] getServiceEntryInstances() {
        return serviceEntryInstances;
    }

    public void setServiceEntryInstances(ServiceEntryInstance[] serviceEntryInstances) {
        this.serviceEntryInstances = serviceEntryInstances;
    }
}
