package com.alpha5.autoaid.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private long CustomerId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false,unique = true)
    private String contactNo;

    @CreationTimestamp
    private Date RegisteredDate;

    @OneToMany(targetEntity = Appointment.class, mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Appointment> appointments;

    @OneToMany(targetEntity = Vehicle.class, mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Vehicle> vehicles;


}
