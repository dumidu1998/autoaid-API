package com.alpha5.autoaid.dto.request;

import com.alpha5.autoaid.enums.UserType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class UpdateStaffRequest {
    private long staffId;
    private String userName;
    private String email;
    private String contactNum;
    private String address;
    private String  city;
    private String  firstName;
    private String  lastName;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private int password[];

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public int isPassword() {
        return password[0];
    }

    public void setPassword(int password[]) {
        this.password = password;
    }
}
