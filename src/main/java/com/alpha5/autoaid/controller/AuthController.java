package com.alpha5.autoaid.controller;

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
    private AuthService AuthService;

    @GetMapping("/login/{email}")
    public String login(@PathVariable String email){
        return AuthService.customerLogin(email);
    }
    @GetMapping("/allcustomers")
    public List<Customer> findAllCustomers(){
        return AuthService.getAll();
    }

}
