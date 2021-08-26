package com.alpha5.autoaid.service;

import com.alpha5.autoaid.dto.request.*;
import com.alpha5.autoaid.dto.response.GetCustomerDetailsRespond;
import com.alpha5.autoaid.dto.response.VehicleDetailsAutofillResponse;
import com.alpha5.autoaid.dto.response.VehicleListResponse;
import com.alpha5.autoaid.enums.RepairStatus;
import com.alpha5.autoaid.enums.ServiceEntryStatus;
import com.alpha5.autoaid.enums.UserStatus;
import com.alpha5.autoaid.enums.UserType;
import com.alpha5.autoaid.model.*;
import com.alpha5.autoaid.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private ServiceEntryRepository serviceEntryRepository;

    @Autowired
    private SectionRepository  sectionRepository;

    public boolean checkIfVehicleExists(String vin){
        if(vehicleRepository.findByVin(vin)!=null){
            return true;
        } return false;
    }

    public GetCustomerDetailsRespond autoFillCustomerDetails(String contact){
        UserData user= userRepository.findByContactNo(contact);
        if (user!=null) {

        GetCustomerDetailsRespond respond=new GetCustomerDetailsRespond();
        List<Vehicle> vehicles=vehicleRepository.findAllByCustomer_UserData_Email(user.getEmail());
        List<VehicleListResponse> vehicleListResponses =new ArrayList<>();

        for (Vehicle vehicle:vehicles){
            VehicleListResponse vehicleListResponse =new VehicleListResponse();
            vehicleListResponse.setVehicleNumber(vehicle.getVehicleNumber());
            vehicleListResponse.setVin(vehicle.getVin());
            vehicleListResponses.add(vehicleListResponse);
        }



            respond.setFirstName(user.getCustomer().getFirstName());
            respond.setLastName(user.getCustomer().getLastName());
            respond.setAddress(user.getAddress());
            respond.setCity(user.getCity());
            respond.setVehicleList(vehicleListResponses);
            return respond ;
        }
        return null;
    }

    public VehicleDetailsAutofillResponse autoFillVehicleDetails(String vin) {
        //check whether vehicle exists
        Vehicle vehicle = vehicleRepository.findByVin(vin);
        if (vehicle != null) {
            VehicleDetailsAutofillResponse response = new VehicleDetailsAutofillResponse();
            response.setVin(vehicle.getVin());
            response.setVehicleNumber(vehicle.getVehicleNumber());
            response.setChassisNo(vehicle.getChassisNo());
            response.setEngineNo(vehicle.getEngineNo());
            response.setMake(vehicle.getMake());
            response.setModel(vehicle.getModel());
            response.setContactNo(vehicle.getCustomer().getUserData().getContactNo());

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
        userData.setUserType(UserType.SKETCHY_CUSTOMER);
        userData.setUserStatus(UserStatus.ACTIVATED);

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
    //Add new Repair
    public long addNewRepair(AddNewRepairsRequest addNewRepairsRequest){
        Vehicle vehicle=vehicleRepository.findByVin(addNewRepairsRequest.getVin());
        Staff serviceAdvisor=staffRepository.findByUserData_Id(addNewRepairsRequest.getUserId());

        Repair newRepair= new Repair();
        newRepair.setPaymentType(addNewRepairsRequest.getPaymentType());
        newRepair.setVehicle(vehicle);
        newRepair.setStaff(serviceAdvisor);
        newRepair.setStatus(RepairStatus.ONGOING);

        repairRepository.save(newRepair);
        Repair repair=repairRepository.findByStatusAndAndVehicle(RepairStatus.ONGOING,vehicle);
        return repair.getRepairId();
    }
    // Add new service entry
    public void addNewServiceEntry(AddNewServiceEntryRequest addNewServiceEntryRequest){
        String out="";
        Staff staff = staffRepository.findByUserData_Id(addNewServiceEntryRequest.getUserId());
        Repair repair = repairRepository.findByRepairId(addNewServiceEntryRequest.getRepairId());

        for (ServiceEntryInstance serviceEntryInstance :addNewServiceEntryRequest.getServiceEntryInstances()) {
            SubCategory subCategory = subCategoryRepository.findBySubCatId(serviceEntryInstance.getSub_cat_id());
            ServiceEntry serviceEntry = new ServiceEntry();
            serviceEntry.setStaff(staff);
            serviceEntry.setDescription(serviceEntryInstance.getDescription());
            serviceEntry.setRepair(repair);
            serviceEntry.setSubCategory(subCategory);
            serviceEntry.setServiceEntryStatus(ServiceEntryStatus.ADDED);

            serviceEntryRepository.save(serviceEntry);
        }
    }

    // get subcategories for sections
    public List<SubCategory> getSubCatList(String sectionName){
        Section section=sectionRepository.findBySectionName(sectionName);
        List<SubCategory> subCategories=subCategoryRepository.findAllBySection(section);
//        List<SubCatListRespond> subCatListResponds=new ArrayList<>();
//
//        for(SubCategory subCategory:subCategories){
//            SubCatListRespond subCatListRespond=new SubCatListRespond();
//            subCatListRespond.setSubCatId(subCategory.getSubCatId());
//            subCatListRespond.setSubcatName(subCategory.getSubCatName());
//            subCatListResponds.add(subCatListRespond);
//        }
        return subCategories;
    }

    public String getNextSlot(long repairId){

        return null;
    }

}
