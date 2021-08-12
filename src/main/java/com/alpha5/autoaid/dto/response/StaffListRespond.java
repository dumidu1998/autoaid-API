package com.alpha5.autoaid.dto.response;

public class StaffListRespond {
    private String firstName;
    private String lastname;
    private  long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }



}

