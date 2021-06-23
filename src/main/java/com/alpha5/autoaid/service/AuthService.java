package com.alpha5.autoaid.service;

import com.alpha5.autoaid.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private CustomerService CustomerService;

    public boolean customerLogin(String email){
        Customer Customer= this.CustomerService.findByEmail(email);
        if (Customer != null){
            return true;
        }
        return  false;
    }
}
