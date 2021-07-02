package com.alpha5.autoaid.service;


import com.alpha5.autoaid.dto.request.CustomerSignInRequest;
import com.alpha5.autoaid.dto.request.StaffLoginRequest;
import com.alpha5.autoaid.dto.response.CustomerSigned;
import com.alpha5.autoaid.dto.response.StaffLogged;
import com.alpha5.autoaid.model.Customer;
import com.alpha5.autoaid.model.Staff;
import com.alpha5.autoaid.repository.CustomerRepository;
import com.alpha5.autoaid.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private CustomerRepository authCustomerRepository;

    @Autowired
    private StaffRepository authStaffRepository;

    @Autowired
    private PasswordEncoder bcryptPasswordEncoder;

    public CustomerSigned signup(Customer customer){
        
        //check for duplicate email
        if(authCustomerRepository.findByEmail(customer.getEmail()) != null){
            //exception
            throw new RuntimeException("Email already taken");
        }
        //check for duplicate mobile number
        if(authCustomerRepository.findByContactNo(customer.getContactNo()) != null){
            throw new RuntimeException("Mobile Number already taken");
        }
        //encode password with bcrypt password
        customer.setPassword(bcryptPasswordEncoder.encode(customer.getPassword()));
        Customer newUser = authCustomerRepository.save(customer);
        CustomerSigned output=new CustomerSigned();
        //set response
        output.setId(newUser.getCustomerId());
        output.setEmail(newUser.getEmail());
        output.setUsername(newUser.getFirstName());

        return output;
    }

    // customer login verification
    public CustomerSigned customerLogin(CustomerSignInRequest signInCustomer){

        // object of relevant customer
        Customer customer= this.authCustomerRepository.findByEmail(signInCustomer.getEmail());

        //decrypt password
        boolean isPasswordMatch = bcryptPasswordEncoder.matches(signInCustomer.getPassword(),customer.getPassword());
        System.out.println("password is "+isPasswordMatch);

        //check whether customer exists
        if( customer == null){

            throw new RuntimeException("Email is Invalid");

        }else if(isPasswordMatch){
            CustomerSigned response=new CustomerSigned();
            response.setId(customer.getCustomerId());
            response.setEmail(customer.getEmail());
            response.setUsername(customer.getFirstName());

            return response;
        }else{

            throw new RuntimeException("Password is wrong. Try again");
        }

    }

    // staff login verification
    public StaffLogged staffLogin(StaffLoginRequest staffLogin){

        // object of relevant customer
        Staff staff= this.authStaffRepository.findByFirstName(staffLogin.getUserName());

        //check whether customer exists



        if( staff == null){

            throw new RuntimeException("User Name is Invalid");

        }else if(staffLogin.getPassword().equals(staff.getPassword())){
            StaffLogged response=new StaffLogged();

            response.setStaffId(staff.getStaffId());
            response.setUserName(staff.getFirstName());
            response.setRole(staff.getRole());

            return response;
        }else{

            throw new RuntimeException("Password is wrong. Try again");
        }

    }
    public List<Customer> getAll(){
        return authCustomerRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = authRepository.findByEmail(email);
        return new User(customer.getEmail(),customer.getPassword(),new ArrayList<>());
    }
}
