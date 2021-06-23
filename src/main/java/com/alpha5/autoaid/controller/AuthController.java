package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class AuthController {
    @Autowired
    private AuthService AuthService;

    @GetMapping("/login/{email}")
    public boolean login(@PathVariable String email){
        return AuthService.customerLogin(email);
    }

}
