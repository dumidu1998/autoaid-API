//package com.alpha5.autoaid.controller;
//
//import com.alpha5.autoaid.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
//@RestController
//public class UserController {
//
//    @Autowired
//    private UserService service;
//
//    @PostMapping("/register")
//    public ResponseEntity<Object> addUser(@RequestBody User user){
//        return service.registerUser(user);
//    }
//
//    @GetMapping("/userDetails")
//    public List<User> findAllUsers(){
//        return service.getUser();
//    }
//    @GetMapping("/userDetails/{id}")
//    public Optional<User> findUserByEmail(@PathVariable int id){
//        return  service.getUserByEmail(id);
//    }
//
//
//}