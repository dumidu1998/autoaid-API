package com.alpha5.autoaid.service;

import com.alpha5.autoaid.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthService {
    @Autowired
    private CustomerService CustomerService;

    public String customerLogin(String email){
        Customer Customer= this.CustomerService.findByEmail(email);
        if (Customer != null){
            return "Customer is Here ";
        }
        return  "Searching Customer is Not in the List";
    }
    public List<Customer> getAll(){
        return CustomerService.getCustomers();
    }
}
