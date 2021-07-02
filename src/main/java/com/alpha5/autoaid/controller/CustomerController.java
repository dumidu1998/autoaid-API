package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.dto.response.CustomerSigned;
import com.alpha5.autoaid.model.Customer;
import com.alpha5.autoaid.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

<<<<<<< HEAD
    //test only
    @PostMapping("auth/siganup")
    public CustomerSigned signupp(@RequestBody Customer customer) {
        CustomerSigned response= customerService.signup(customer);
        return response;
    }
=======

//    @PostMapping("auth/siganup")
//    public CustomerSigned signupp(@RequestBody Customer customer) {
//        CustomerSigned response= customerService.signup(customer);
//        return response;
//    }
>>>>>>> abd9fb7bcf15ccd5f559df6a67c63087dba4e3f0
}
