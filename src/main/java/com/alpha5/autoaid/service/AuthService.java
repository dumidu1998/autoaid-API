package com.alpha5.autoaid.service;


import com.alpha5.autoaid.dto.request.CustomerSignInRequest;
import com.alpha5.autoaid.dto.request.CustomerSignUpRequest;
import com.alpha5.autoaid.dto.request.StaffLoginRequest;
import com.alpha5.autoaid.dto.response.CustomerSigned;
import com.alpha5.autoaid.dto.response.StaffLogged;
import com.alpha5.autoaid.model.Customer;
import com.alpha5.autoaid.model.Staff;
import com.alpha5.autoaid.model.UserData;
import com.alpha5.autoaid.repository.CustomerRepository;
import com.alpha5.autoaid.repository.StaffRepository;
import com.alpha5.autoaid.repository.UserRepository;
import com.alpha5.autoaid.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
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
    private UserRepository userRepository;

    @Autowired
    private StaffRepository authStaffRepository;

    @Autowired
    private PasswordEncoder bcryptPasswordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    //return true if username or email exists
    public boolean findbyUserNameorEmail(String username, String email) {
        if (userRepository.findByUserNameOrEmail(username, email) != null) {
            return true;
        }
        return false;
    }

    //check whether contact No exists
    public boolean checkIfContactExists(String contactNo) {
        if (userRepository.findByContactNo(contactNo) != null) {
            return true;
        }
        return false;
    }

    //check for email
    public boolean checkIfEmailExists(String email) {
        if (userRepository.findByEmail(email) != null) {
            return true;
        }
        return false;
    }

    //check for username
    public boolean checkIfUserNameExists(String username) {
        if (userRepository.findByUserName(username) != null) {
            return true;
        }
        return false;
    }

    public void signup(CustomerSignUpRequest customerSignUpRequest) {

        UserData userData = new UserData();
        Customer customer = new Customer();

        //encode password with bcrypt password
        userData.setPassword(bcryptPasswordEncoder.encode(customerSignUpRequest.getPassword()));
        userData.setEmail(customerSignUpRequest.getEmail());
        userData.setUserName(customerSignUpRequest.getUserName());
        userData.setContactNo(customerSignUpRequest.getContactNo());
        userData.setAddress(customerSignUpRequest.getAddress());
        userData.setCity(customerSignUpRequest.getCity());


        //set details to customer object
        customer.setFirstName(customerSignUpRequest.getFirstName());
        customer.setLastName(customerSignUpRequest.getLastName());
        customer.setUserData(userData);

        //save user login data and customer data
        userRepository.save(userData);
        authCustomerRepository.save(customer);

    }

    // customer login verification
    public CustomerSigned customerLogin(CustomerSignInRequest customerSignInRequest) {
        // object of relevant user
        UserData customer = this.userRepository.findByUserNameOrEmail(customerSignInRequest.getUserName(), customerSignInRequest.getEmail());

        //check password and with the user email with authentication manager
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(customer.getEmail(), customerSignInRequest.getPassword())
            );
        } catch (Exception ex) {
            //throw error if emails and password does not match
            throw new RuntimeException("Invalid Password");
        }
        //get jwt token
        String token = jwtTokenUtil.generateToken(customerSignInRequest.getEmail());

        CustomerSigned response = new CustomerSigned();
        response.setId(customer.getId());
        response.setEmail(customer.getEmail());
        response.setUsername(customer.getUserName());
        response.setToken(token); //append to response entity
        return response;
    }

    // staff login verification
    public StaffLogged staffLogin(StaffLoginRequest staffLogin) {

        // object of relevant customer
        Staff staff = this.authStaffRepository.findByFirstName(staffLogin.getUserName());

        //check whether customer exists
        if (staff == null) {
            throw new RuntimeException("User Name is Invalid");
        } else {
            //check password and email with authentication manager
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(staffLogin.getUserName(), staffLogin.getPassword())
                );
            } catch (Exception ex) {
                //throw error if emaila and password does not match
                throw new RuntimeException("Email and Password is Not matching");
            }
            //get jwt token
            String token = jwtTokenUtil.generateToken(staffLogin.getUserName());
            StaffLogged response = new StaffLogged();

            response.setStaffId(staff.getStaffId());
            response.setJwt(token);
            response.setUserName(staff.getFirstName());
            response.setRole(staff.getRole());

            return response;
        }
    }

    public List<Customer> getAll() {
        return authCustomerRepository.findAll();
    }

    //return customer email and password to the web security configurer user details according to the given email.

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {

        UserData userData = userRepository.findByEmail(email);

        //returning user details to the web security configurer user details according to the requested details
        return new User(userData.getEmail(), userData.getPassword(), new ArrayList<>());
    }

}
