package com.alpha5.autoaid.service;

import com.alpha5.autoaid.dto.request.AddSketchyCustomerRequest;
import com.alpha5.autoaid.dto.request.AddVehicleRequest;
import com.alpha5.autoaid.dto.request.VehicleDetailsAutofillRequest;
import com.alpha5.autoaid.dto.response.GetCustomerDetailsRespond;
import com.alpha5.autoaid.dto.response.VehicleDetailsAutofillResponse;
import com.alpha5.autoaid.enums.UserType;
import com.alpha5.autoaid.model.Customer;
import com.alpha5.autoaid.model.UserData;
import com.alpha5.autoaid.model.Vehicle;
import com.alpha5.autoaid.repository.CustomerRepository;
import com.alpha5.autoaid.repository.StaffRepository;
import com.alpha5.autoaid.repository.UserRepository;
import com.alpha5.autoaid.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

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

    @Autowired
    private PasswordEncoder bcryptPasswordEncoder;

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

    //adding new vehicle
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
    //add customer which not exists
    public void addNewCustomerSketchy(AddSketchyCustomerRequest addSketchyCustomerRequest){
        UserData userData= new UserData();
        Customer customer= new Customer();
        //set default password for sketchy users
        String defaultPassword="User123";
        String userName="user_"+generateRandomUserName();

        while (checkWhetherUserNameExists(userName)){
            userName="user_"+generateRandomUserName();
        }
        //set unique email
        String email=userName+"@example.com";

        //set user data object
        userData.setContactNo(addSketchyCustomerRequest.getContactNo());
        userData.setUserName(userName);
        userData.setPassword(bcryptPasswordEncoder.encode(defaultPassword));
        userData.setEmail(email);
        userData.setAddress(addSketchyCustomerRequest.getAddress());
        userData.setCity(addSketchyCustomerRequest.getCity());
        userData.setUserType(UserType.SketchyCustomer);

        //set Customer Object
        customer.setFirstName(addSketchyCustomerRequest.getFirstName());
        customer.setLastName(addSketchyCustomerRequest.getLastName());
        customer.setUserData(userData);

        //save user login data and customer data
        userRepository.save(userData);
        customerRepository.save(customer);
    }

    //check whether randomly generated user name exists
    protected boolean checkWhetherUserNameExists(String userName){
        if(userRepository.findByUserName(userName)!=null){
            return true;
        }else return false;
    }

    protected String generateRandomUserName(){
        String keys = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        while (stringBuilder.length() < 10) { // length of the random string.
            int index = (int) (random.nextFloat() * keys.length());
            stringBuilder.append(keys.charAt(index));
        }
        String randomKey = stringBuilder.toString();
        return randomKey;
    }

}
