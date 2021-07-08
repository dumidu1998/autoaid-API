package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
    Vehicle findByVehicleNumber(String vehicleNumber);
    List<Vehicle> findAllByCustomer_Email(String email);
    List<Vehicle> findAllByCustomer_CustomerId(long id);
}
