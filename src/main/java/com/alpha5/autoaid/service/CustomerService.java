package com.alpha5.autoaid.service;

import com.alpha5.autoaid.model.Customer;
import com.alpha5.autoaid.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository CustomerRepository;

    public Customer findByEmail(String email){
        return CustomerRepository.findByEmail(email);
    }

}
