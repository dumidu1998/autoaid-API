package com.alpha5.autoaid.model;


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
    private int vehicleId;

    @Column(nullable = false)
    private String vin;

    @Column(nullable = false)
    private String engineNo;

    @Column(nullable = false)
    private String chassisNo;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @ManyToOne
    @JoinColumn(name = "CustomerId")
    Customer customer;

    @OneToMany(targetEntity = Repair.class, mappedBy = "vehicle", cascade = CascadeType.ALL)
    private Set<Repair> repairs;



}
