package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.model.User;
import com.alpha5.autoaid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<Boolean> addUser(@RequestBody User user){
        return service.registerUser(user);
    }


    @GetMapping("/checkemail/{email}")
    public ResponseEntity<Boolean> checkemail(@PathVariable String email){
        return service.checkemail(email);
    }

    @GetMapping("/checkmobile/{mobile}")
    public ResponseEntity<Boolean> checkmobile(@PathVariable String mobile){
        return service.checkContact(mobile);
    }

//    public List<User> getAllUsers(){
//        return service.getUsers()
//    }

}
