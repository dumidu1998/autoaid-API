package com.alpha5.autoaid.service;


import com.alpha5.autoaid.dto.request.AddStaffRequest;
import com.alpha5.autoaid.dto.response.AddStaffRespond;
import com.alpha5.autoaid.dto.response.AdminListRespond;
import com.alpha5.autoaid.dto.response.GetStaffMemInfoRespond;
import com.alpha5.autoaid.model.Staff;
import com.alpha5.autoaid.model.UserData;
import com.alpha5.autoaid.dto.response.*;
import com.alpha5.autoaid.enums.StaffRole;
import com.alpha5.autoaid.repository.StaffRepository;
import com.alpha5.autoaid.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;
import javax.swing.text.SimpleAttributeSet;
import java.awt.font.TextHitInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.SocketHandler;

@Service
public class AdminService {
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    //-----------_______________________--------------------ADD to TO Real ------------____________________-----//

    //------------------Staff Add------------------//
    public AddStaffRespond insertStaff(AddStaffRequest addStaffRequest){
    //object 2
        UserData userData = new UserData();
        Staff staff =new Staff();
        //assign values to user data
        userData.setPassword(passwordEncoder.encode(addStaffRequest.getPassword()));
        userData.setAddress(addStaffRequest.getAddress());
        userData.setCity(addStaffRequest.getCity());
        userData.setContactNo(addStaffRequest.getContactNum());
        userData.setEmail(addStaffRequest.getEmail());
        userData.setUserName(addStaffRequest.getUserName());
        userData.setUserType("1");
        //assign values ti staff

        UserData newuserdata = userRepository.save(userData);

        staff.setFirstName(addStaffRequest.getFirstName());
        staff.setLastName(addStaffRequest.getLastName());
        staff.setRole(addStaffRequest.getRole());
        staff.setUserData(newuserdata);

        Staff savedstaff=staffRepository.save(staff);

        AddStaffRespond addStaffRespond =new AddStaffRespond();
        addStaffRespond.setFisrtName(savedstaff.getFirstName());
        addStaffRespond.setSid(savedstaff.getStaffId());

        return addStaffRespond;
    }

    //------XXX------------Staff Add-----XXX-------------//



    //------------------Staff Handling NavBar Data------------------//
    public List<AdminListRespond> getAdmins(){
        List<Staff> admins= staffRepository.findAllByRole(StaffRole.ADMIN);
        List<AdminListRespond> outlist=new ArrayList<AdminListRespond>();
        for (Staff entity: admins){
            AdminListRespond adminList=new AdminListRespond();
            adminList.setFirstName(entity.getFirstName());
            adminList.setLastname(entity.getLastName());
            adminList.setId(entity.getStaffId());
            outlist.add(adminList);
        }
        return outlist;
    }

    //------------XX------Staff Handling NavBar Data------XX------------//

    //-------------------get nxt staff Id to the form ------------------//
    public long getNewStaffId(){
        long getnewid=staffRepository.getMaxStaffId();
        return getnewid+1;
    }
    //---------XXX----------get nxt staff Id to the form ---------XX---------//

    //-------------------get nxt staff Mem Info ------------------//
    public GetStaffMemInfoRespond getStaffMemInfo(long sid){
        Staff memInfo = staffRepository.findByStaffId(sid);
        GetStaffMemInfoRespond response =new GetStaffMemInfoRespond();
        response.setFirstName(memInfo.getFirstName());
        response.setLastName(memInfo.getLastName());
        response.setRole(memInfo.getRole());

        UserData user_data= memInfo.getUserData();
        response.setAddress(user_data.getAddress());
        response.setCity(user_data.getCity());
        response.setEmail(user_data.getEmail());
        response.setPassword(user_data.getPassword());

        response.setContactNum(user_data.getContactNo());
        response.setUserName(user_data.getUserName());
        return response;
    }



//-----------XXXX--------get nxt staff Mem Info -------XXX-----------//


}

