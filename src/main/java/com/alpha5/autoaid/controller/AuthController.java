package com.alpha5.autoaid.controller;


import com.alpha5.autoaid.dto.response.UserSignup;
import com.alpha5.autoaid.model.Customer;
import com.alpha5.autoaid.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ="http://localhost:3000", maxAge = 3600)
@RestController
//@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/auth/signup")
    public UserSignup signup(@RequestBody Customer customer){
        UserSignup response= authService.signup(customer);
        return response;
    }

    @GetMapping("/login/{email}")
    public String login(@PathVariable String email){
        return authService.customerLogin(email);
    }

    @GetMapping("/allcustomers")
    public List<Customer> findAllCustomers(){
        return authService.getAll();
    }
    

}
