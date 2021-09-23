package com.alpha5.autoaid.service;

import com.alpha5.autoaid.dto.request.CustomerProfileDetailsRequest;
import com.alpha5.autoaid.dto.request.CustomerProfileUpdateRequest;
import com.alpha5.autoaid.dto.response.customer.GetProfileDetailsResponse;
import com.alpha5.autoaid.model.Customer;
import com.alpha5.autoaid.model.UserData;
import com.alpha5.autoaid.model.Vehicle;
import com.alpha5.autoaid.repository.CustomerRepository;
import com.alpha5.autoaid.repository.UserRepository;
import com.alpha5.autoaid.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Vehicle> getVehicleById(long id) {
        return vehicleRepository.findAllByCustomer_CustomerId(id);
    }

    public List<Vehicle> getVehicleByEmail(String email) {
        return vehicleRepository.findAllByCustomer_UserData_Email(email);
    }
    public boolean checkEmailExistsInOtherUsers(String email, long userId){
        if (customerRepository.findByUserData_Email(email)==customerRepository.findByUserData_Id(userId)){
            return false;
        }else if (userRepository.findByEmail(email)!=null){
            return true;
        }else return false;
    }

    public boolean checkContactExistsInOtherUsers(String contact, long userId){
        if (customerRepository.findByUserData_ContactNo(contact)==customerRepository.findByUserData_Id(userId)){
            return false;
        }else if (userRepository.findByContactNo(contact)!=null){
            return true;
        }else return false;
    }
    public GetProfileDetailsResponse getProfileDetails(CustomerProfileDetailsRequest customerProfileDetailsRequest){
        UserData userData=userRepository.findByIdAndUserName(customerProfileDetailsRequest.getUserId(),customerProfileDetailsRequest.getUserName());
        Customer customer=customerRepository.findByUserData(userData);

        GetProfileDetailsResponse getProfileDetailsResponse=new GetProfileDetailsResponse();
        getProfileDetailsResponse.setFirstName(customer.getFirstName());
        getProfileDetailsResponse.setLastName(customer.getLastName());
        getProfileDetailsResponse.setAddress(userData.getAddress());
        getProfileDetailsResponse.setCity(userData.getCity());
        getProfileDetailsResponse.setEmail(userData.getEmail());
        getProfileDetailsResponse.setContactNo(userData.getContactNo());
        getProfileDetailsResponse.setUserName(userData.getUserName());

        return getProfileDetailsResponse;
    }
    public void updateUser(CustomerProfileUpdateRequest customerProfileUpdateRequest){
        UserData userData=userRepository.findByIdAndUserType(customerProfileUpdateRequest.getUserId(),customerProfileUpdateRequest.getUserType());
        Customer customer=customerRepository.findByUserData(userData);
        userData.setCity(customerProfileUpdateRequest.getCity());
        userData.setContactNo(customerProfileUpdateRequest.getContactNo());
        userData.setEmail(customerProfileUpdateRequest.getEmail());
        userData.setAddress(customerProfileUpdateRequest.getAddress());
        customer.setFirstName(customerProfileUpdateRequest.getFirstName());
        customer.setLastName(customerProfileUpdateRequest.getLastName());
        userRepository.save(userData);
        customerRepository.save(customer);
    }
    public boolean findByUserIdAndUsername(CustomerProfileDetailsRequest customerProfileDetailsRequest){
        if (userRepository.findByIdAndUserName(customerProfileDetailsRequest.getUserId(),customerProfileDetailsRequest.getUserName())==null){
            return false;
        }else return true;
    }

}
