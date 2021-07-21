package com.alpha5.autoaid.service;

import com.alpha5.autoaid.dto.response.ExpenseResponse;
import com.alpha5.autoaid.model.Vehicle;
import com.alpha5.autoaid.repository.CustomerRepository;
import com.alpha5.autoaid.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    CustomerRepository customerRepository;

    public List<Vehicle> getVehicleById(long id) {
        return vehicleRepository.findAllByCustomer_CustomerId(id);
    }

    public List<Vehicle> getVehicleByEmail(String email) {
        return vehicleRepository.findAllByCustomer_UserData_Email(email);
    }

    public ExpenseResponse getSummaryByCustomer(long id) {
        float totalSpent = customerRepository.customerSummary(id);
        float totalSpentMonth = customerRepository.customerSummary2(id);
        float  repairsPerMonth = customerRepository.customerSummary3(id);
        int  activeRepairs = customerRepository.customerSummary4(id);
        return new ExpenseResponse(totalSpent,totalSpentMonth,totalSpent/12,repairsPerMonth,activeRepairs);
    }
}
