package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.VehicleDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<VehicleDetails,Long> {
    VehicleDetails findByVehicleNumber(String vehicleNumber);
}
