package com.alpha5.autoaid.service;

import com.alpha5.autoaid.model.Vehicle;
import com.alpha5.autoaid.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    public Vehicle getVehicleById(String i) {
        return vehicleRepository.findByCustomer_FirstName(i);
    }
}
