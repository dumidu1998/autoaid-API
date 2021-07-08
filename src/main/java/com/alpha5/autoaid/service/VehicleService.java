package com.alpha5.autoaid.service;

import com.alpha5.autoaid.model.Vehicle;
import com.alpha5.autoaid.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    public List<Vehicle> getVehicleById(Long i) {
        return vehicleRepository.findAllByCustomer_CustomerId(i);
    }
}
