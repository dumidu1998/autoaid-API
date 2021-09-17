package com.alpha5.autoaid.model;

import com.alpha5.autoaid.enums.UserStatus;
import com.alpha5.autoaid.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserData {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false,unique = true)
    private String userName;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false,unique = true)
    private String contactNo;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String address;

    @Column(nullable = true)
    private String city;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @OneToOne(targetEntity = Customer.class, mappedBy = "userData", cascade = CascadeType.ALL)
    @JsonIgnore
    private Customer customer;

    @OneToOne (targetEntity = Staff.class, mappedBy = "userData", cascade = CascadeType.ALL)
    @JsonIgnore
    private Staff staff;

}
