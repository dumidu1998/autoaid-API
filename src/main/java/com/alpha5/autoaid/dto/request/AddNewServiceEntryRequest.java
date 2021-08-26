package com.alpha5.autoaid.dto.request;

public class AddNewServiceEntryRequest {
     private long staffId;
     private long repairId;
     private ServiceEntryInstance[] serviceEntryInstances;

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
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
