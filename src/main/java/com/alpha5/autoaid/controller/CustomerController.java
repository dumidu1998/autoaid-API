package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/customer")
@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;


}
