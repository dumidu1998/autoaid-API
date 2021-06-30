package com.alpha5.autoaid.controller;


import com.alpha5.autoaid.dto.request.CustomerSignInRequest;
import com.alpha5.autoaid.dto.request.StaffLoginRequest;
import com.alpha5.autoaid.dto.response.CustomerSigned;
import com.alpha5.autoaid.dto.response.StaffLogged;
import com.alpha5.autoaid.model.Customer;
import com.alpha5.autoaid.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ="http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public CustomerSigned signup(@RequestBody Customer customer){
        CustomerSigned response= authService.signup(customer);
        return response;
    }

    @PostMapping("/customer/login")
    public CustomerSigned customerLogin(@RequestBody CustomerSignInRequest signInCustomer){
        CustomerSigned response= authService.customerLogin(signInCustomer);
        return response;
    }

    @PostMapping("/staff")
    public StaffLogged staffLogin(@RequestBody StaffLoginRequest loginStaff){
        StaffLogged response=authService.staffLogin(loginStaff);

        return response;
    }

    @GetMapping("/allcustomers")
    public List<Customer> findAllCustomers(){
        return authService.getAll();
    }
    

}
