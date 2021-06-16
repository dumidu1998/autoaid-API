package com.alpha5.autoaid.service;

import com.alpha5.autoaid.model.User;
import com.alpha5.autoaid.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;



    public ResponseEntity<Object> registerUser(User user){
        Optional<User> sameEmail =repository.findByEmail(user.getEmail());
        Optional<User> sameMobile =repository.findByContactNo(user.getContactNo());
        if(sameEmail.isPresent()){
            return new ResponseEntity<>("Email taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(sameMobile.isPresent()){
            return new ResponseEntity<>("mobile taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        repository.save(user);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }

}
