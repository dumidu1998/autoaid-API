package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.model.User;
import com.alpha5.autoaid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<Object> addUser(@RequestBody User user){
        return service.registerUser(user);
    }


}
