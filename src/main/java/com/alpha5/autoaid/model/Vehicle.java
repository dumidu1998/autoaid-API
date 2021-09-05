package com.alpha5.autoaid.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vehicle {
    @Id
    @GeneratedValue
    private long vehicleId;

    @Column(nullable = false)
    private String vin;

    @Column(nullable = false)
    private String vehicleNumber;

    @Column(nullable = false)
    private String engineNo;

    @Column(nullable = false)
    private String chassisNo;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @ManyToOne
    @JsonIgnore
    private Customer customer;

    @OneToMany(targetEntity = Repair.class, mappedBy = "vehicle", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Repair> repairs;

    @OneToMany(targetEntity = Appointment.class, mappedBy = "vehicle", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Appointment> appointments;


}
