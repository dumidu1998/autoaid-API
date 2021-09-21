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

    @Column
    private String vin;

    @Column
    private String vehicleNumber;

    @Column
    private String engineNo;

    @Column
    private String chassisNo;

    @Column
    private String make;

    @Column
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
