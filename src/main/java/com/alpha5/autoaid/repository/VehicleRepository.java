package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
    Vehicle findByVehicleNumber(String vehicleNumber);
    Vehicle findByCustomer_FirstName(String s);
}
