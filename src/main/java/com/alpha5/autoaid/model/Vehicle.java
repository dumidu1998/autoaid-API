package com.alpha5.autoaid.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="VEHICLE")
public class Vehicle {
    @Id
    @GeneratedValue
    private int id;
    private int userid;
    private String VIN;
    private String engineNo;
    private String chassisNo;


}
