package com.alpha5.autoaid.service;

import com.alpha5.autoaid.model.User;
import com.alpha5.autoaid.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;


    public User addUser(User user){
        return repository.save(user);
    }

    public List<User> addUsers(List<User> user){
        return repository.saveAll(user);
    }

    public List<User> getUsers(){
        return repository.findAll();
    }

    public User getUserById(int id){
        return repository.findById(id).orElse(null);
    }

    public User getUserByName(String name){
        return repository.findByname(name);
    }

    public String deleteUser(int id){
        repository.deleteById(id);
        return "User Deleted!" + id;
    }

    public User updateUser(User user){
        User existingUser =  repository.findById(user.getId()).orElse(null);
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        return repository.save(existingUser);
    }
}
