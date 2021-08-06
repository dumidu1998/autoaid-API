package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Vehicle findByVehicleNumber(String vehicleNumber);
    Vehicle findByVin(String vin);
    List<Vehicle> findAllByCustomer_UserData_Email(String email);

    List<Vehicle> findAllByCustomer_CustomerId(long id);
}
