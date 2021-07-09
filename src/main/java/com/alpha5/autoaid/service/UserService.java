//package com.alpha5.autoaid.service;
//
//import com.alpha5.autoaid.model.User;
//import com.alpha5.autoaid.model.Vehicle;
//import com.alpha5.autoaid.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserService {
//    @Autowired
//    private UserRepository repository;
//
//    public ResponseEntity<Object> registerUser(User user){
//        Optional<User> sameEmail =repository.findByEmail(user.getEmail());
//        Optional<User> sameMobile =repository.findByContactNo(user.getContactNo());
//
//        if(sameEmail.isPresent()){
//            return new ResponseEntity<>("Email Already Taken", HttpStatus.UNAUTHORIZED);
//        }
//        if(sameMobile.isPresent()){
//            return new ResponseEntity<>("Already Registered with the Mobile Number", HttpStatus.UNAUTHORIZED);
//        }
//        repository.save(user);
//        return new ResponseEntity<>(true,HttpStatus.OK);
//    }
//
//    public List<User> getUser(){
//        return repository.findAll();
//    }
//    //get by id
//
//    public Optional<User> getUserByEmail(int id){
//
//        return repository.findById(id);
//    }
//
//}
