package com.alpha5.autoaid.service;


import com.alpha5.autoaid.dto.response.CustomerSigned;
import com.alpha5.autoaid.model.Customer;
import com.alpha5.autoaid.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private CustomerService customerService;



    public CustomerSigned signup(Customer customer){
        if(authRepository.findByEmail(customer.getEmail()) != null){
            throw new RuntimeException("Email already taken");
        }
        if(authRepository.findByContactNo(customer.getContactNo()) != null){
            throw new RuntimeException("Mobile Number already taken");
        }
        Customer newUser = authRepository.save(customer);
        CustomerSigned output=new CustomerSigned();

        output.setId(newUser.getCustomerId());
        output.setEmail(newUser.getEmail());
        output.setUsername(newUser.getFirstName());

        return output;
    }

    public String customerLogin(String email){
        Customer Customer= this.authRepository.findByEmail(email);
        if (Customer != null){
            return "Customer is Here ";
        }
        return  "Searching Customer is Not in the List";
    }

    public List<Customer> getAll(){
        return authRepository.findAll();
    }

}
