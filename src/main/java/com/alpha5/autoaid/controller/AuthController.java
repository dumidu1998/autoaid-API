package com.alpha5.autoaid.controller;


import com.alpha5.autoaid.dto.response.UserSignup;
import com.alpha5.autoaid.model.Customer;
import com.alpha5.autoaid.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/auth/signup")
    public UserSignup signup(@RequestBody Customer customer){
        UserSignup response= authService.signup(customer);
        return response;
    }

}
