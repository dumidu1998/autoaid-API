package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.model.User;
import com.alpha5.autoaid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public User addUser(@RequestBody User user){
        return service.registerUser(user);
    }

    @PostMapping("/addUsers")
    public List<User> addUsers(@RequestBody List<User> user){
        return service.addUsers(user);
    }

//    public List<User> getAllUsers(){
//        return service.getUsers()
//    }

}
