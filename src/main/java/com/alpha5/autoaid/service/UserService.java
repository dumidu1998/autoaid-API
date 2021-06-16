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



    public ResponseEntity<Boolean> registerUser(User user){
        Optional<User> userOptional =repository.findByEmail(user.getEmail());
        if(userOptional.isEmpty()){
            repository.save(user);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Boolean> checkemail(String email) {
        Optional<User> user = repository.findByEmail(email);
        if(!user.isEmpty()){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Boolean> checkContact(String contactno) {
        Optional<User> user = repository.findByContactNo(contactno);
        if(user.isPresent()){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
