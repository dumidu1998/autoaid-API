package com.alpha5.autoaid.service;

import com.alpha5.autoaid.dto.request.AddVehicleRequest;
import com.alpha5.autoaid.dto.request.VehicleDetailsAutofillRequest;
import com.alpha5.autoaid.dto.response.VehicleDetailsAutofillResponse;
import com.alpha5.autoaid.model.Customer;
import com.alpha5.autoaid.model.Vehicle;
import com.alpha5.autoaid.repository.CustomerRepository;
import com.alpha5.autoaid.repository.StaffRepository;
import com.alpha5.autoaid.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceAdvisorService {
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public VehicleDetailsAutofillResponse autoFillVehicleDetails(VehicleDetailsAutofillRequest vehicleDetailsAutofillRequest) {
        //check whether vehicle exists
        Vehicle vehicle = vehicleRepository.findByVehicleNumber(vehicleDetailsAutofillRequest.getVehicleNumber());
        if (vehicle != null) {
            VehicleDetailsAutofillResponse response = new VehicleDetailsAutofillResponse();
            response.setVehiceId(vehicle.getVehicleId());
            response.setChassisNo(vehicle.getChassisNo());
            response.setEnginNo(vehicle.getEngineNo());
            response.setMake(vehicle.getMake());
            response.setModel(vehicle.getModel());

            return response;
        } else throw new RuntimeException("Vehicle not registered. Add details");

    }

    public String registerNewVehicle(AddVehicleRequest addVehicleRequest) {
//        Optional<Customer> customerOptional = customerRepository.findById(addVehicleRequest.getCustomerId());
//        if (!customerOptional.isPresent()) {
//            throw new RuntimeException("no customer");
//        }
//        Vehicle vehicle=new Vehicle();
//        vehicle.setCustomer(customerOptional.get());
//        Vehicle newVehicle = vehicleRepository.save(vehicle);
        return "Vehicle Added Successfully";
    }

}
