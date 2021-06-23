package com.alpha5.autoaid.service;


import com.alpha5.autoaid.dto.response.UserSignup;
import com.alpha5.autoaid.model.Customer;
import com.alpha5.autoaid.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    public UserSignup signup(Customer customer){
        if(authRepository.findByEmail(customer.getEmail()) != null){
            throw new RuntimeException("Email already taken");
        }
        if(authRepository.findByContactNo(customer.getContactNo()) != null){
            throw new RuntimeException("Mobile Number already taken");
        }
        Customer newUser = authRepository.save(customer);
        UserSignup output=new UserSignup();

        output.setId(newUser.getCustomerId());
        output.setEmail(newUser.getEmail());
        output.setUsername(newUser.getFirstName());

        return output;
    }

}
