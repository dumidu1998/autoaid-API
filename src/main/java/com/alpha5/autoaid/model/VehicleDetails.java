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
public class VehicleDetails {
    @Id
    @GeneratedValue
    private int vehicleId;

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
    @JoinColumn(name = "Customer_id")
    Customer customer;

    @OneToMany(targetEntity = Repair.class, mappedBy = "vehicleDetails", cascade = CascadeType.ALL)
    private Set<Repair> repairs;

    public int getVehicleId() {
        return vehicleId;
    }

    public String getVin() {
        return vin;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public String getChassisNo() {
        return chassisNo;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Set<Repair> getRepairs() {
        return repairs;
    }
}
