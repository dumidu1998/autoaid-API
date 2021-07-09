package com.alpha5.autoaid.controller;


import com.alpha5.autoaid.dto.request.CustomerSignInRequest;
import com.alpha5.autoaid.dto.request.CustomerSignUpRequest;
import com.alpha5.autoaid.dto.request.StaffLoginRequest;
import com.alpha5.autoaid.dto.response.CustomerSigned;
import com.alpha5.autoaid.dto.response.StaffLogged;
import com.alpha5.autoaid.model.Customer;
import com.alpha5.autoaid.service.AuthService;
import com.alpha5.autoaid.util.JwtTokenUtil;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody CustomerSignUpRequest customerSignUpRequest){


        String email=customerSignUpRequest.getEmail();
        String username=customerSignUpRequest.getUserName();
        String contactNo=customerSignUpRequest.getContactNo();
        String errorMsg;
        if (authService.checkIfEmailExists(email)){
            errorMsg="Email exists";
        }else if (authService.checkIfContactExists(contactNo)){
            errorMsg="Contact exists";
        }else if (authService.checkIfUserNameExists(username)){
            errorMsg="username exists";
        }else {
            CustomerSigned response = authService.signup(customerSignUpRequest);
            return ResponseEntity.ok().body(response);
        }return ResponseEntity.badRequest().body(errorMsg);
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

    @PostMapping("/gettoken")
    public String generateToken(@RequestBody CustomerSignInRequest signInRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword())
            );
        }catch (Exception ex){
            throw new Exception("Invalid Username or password");
        }
        return jwtTokenUtil.generateToken(signInRequest.getEmail());
    }

    @GetMapping("/allcustomers")
    public List<Customer> findAllCustomers(){
        return authService.getAll();
    }

}
