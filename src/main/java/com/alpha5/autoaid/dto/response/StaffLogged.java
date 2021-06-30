package com.alpha5.autoaid.dto.response;

import com.alpha5.autoaid.enums.StaffRole;

public class StaffLogged {
    private long staffId;
    private String userName;
    private StaffRole role;

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public StaffRole getRole() {
        return role;
    }

    public void setRole(StaffRole role) {
        this.role = role;
    }
}
