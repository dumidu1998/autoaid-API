package com.alpha5.autoaid.service;

import com.alpha5.autoaid.dto.request.AddVehicleRequest;
import com.alpha5.autoaid.dto.request.VehicleDetailsAutofillRequest;
import com.alpha5.autoaid.dto.response.GetCustomerDetailsRespond;
import com.alpha5.autoaid.dto.response.VehicleDetailsAutofillResponse;
import com.alpha5.autoaid.model.Customer;
import com.alpha5.autoaid.model.UserData;
import com.alpha5.autoaid.model.Vehicle;
import com.alpha5.autoaid.repository.CustomerRepository;
import com.alpha5.autoaid.repository.StaffRepository;
import com.alpha5.autoaid.repository.UserRepository;
import com.alpha5.autoaid.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceAdvisorService {
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    public boolean checkIfVehicleExists(String vin){
        if(vehicleRepository.findByVin(vin)!=null){
            return true;
        } return false;
    }

    public GetCustomerDetailsRespond autoFillCustomerDetails(String contact){
        UserData user= userRepository.findByContactNo(contact);
        GetCustomerDetailsRespond respond=new GetCustomerDetailsRespond();
        if (user!=null) {
            respond.setFirstName(user.getCustomer().getFirstName());
            respond.setLastName(user.getCustomer().getLastName());
            respond.setAddress(user.getAddress());
            respond.setCity(user.getCity());
            return respond ;
        }
        return null;
    }

    public VehicleDetailsAutofillResponse autoFillVehicleDetails(VehicleDetailsAutofillRequest vehicleDetailsAutofillRequest) {
        //check whether vehicle exists
        Vehicle vehicle = vehicleRepository.findByVin(vehicleDetailsAutofillRequest.getVin());
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

    public void registerNewVehicle(AddVehicleRequest addVehicleRequest) {
        //get customer data by search contact in user data

        Customer customer=customerRepository.findByUserData(userRepository.findByContactNo(addVehicleRequest.getContactNo()));
        Vehicle newVehicle=new Vehicle();
        newVehicle.setVehicleNumber(addVehicleRequest.getVehicleNumber());
        newVehicle.setVin(addVehicleRequest.getVin());
        newVehicle.setChassisNo(addVehicleRequest.getChassisNo());
        newVehicle.setEngineNo(addVehicleRequest.getEngineNo());
        newVehicle.setMake(addVehicleRequest.getMake());
        newVehicle.setModel(addVehicleRequest.getModel());
        newVehicle.setCustomer(customer);

        vehicleRepository.save(newVehicle);
    }

}
