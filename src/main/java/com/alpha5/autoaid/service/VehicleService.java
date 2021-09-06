package com.alpha5.autoaid.service;

import com.alpha5.autoaid.dto.response.ExpenseResponse;
import com.alpha5.autoaid.model.Vehicle;
import com.alpha5.autoaid.repository.CustomerRepository;
import com.alpha5.autoaid.repository.UserRepository;
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
    @Autowired
    UserRepository userRepository;

    public List<Vehicle> getVehicleById(long id) {
        return vehicleRepository.findAllByCustomer_CustomerId(id);
    }

    public List<Vehicle> getVehicleByEmail(String email) {
        return vehicleRepository.findAllByCustomer_UserData_Email(email);
    }

    public ExpenseResponse getSummaryByCustomer(long id) {
        long cusId = customerRepository.findByUserData_Id(id).getCustomerId();
        float totalSpent = customerRepository.customerSummary(cusId);
        float totalSpentMonth = customerRepository.customerSummary2(cusId);
        float  repairsPerMonth = customerRepository.customerSummary3(cusId);
        int  activeRepairs = customerRepository.customerSummary4(cusId);
        return new ExpenseResponse(totalSpent,totalSpentMonth,totalSpent/12,repairsPerMonth,activeRepairs);
    }

    public List<Vehicle> getVehicleByUserId(long id) {
        return vehicleRepository.findAllByCustomer_UserData_Id(id);
    }
}
