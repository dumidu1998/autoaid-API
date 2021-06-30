package com.alpha5.autoaid.service;


import com.alpha5.autoaid.dto.request.CustomerSignInRequest;
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

    // customer login verification
    public CustomerSigned customerLogin(CustomerSignInRequest signInCustomer){

        // object of relevant customer
        Customer customer= this.authRepository.findByEmail(signInCustomer.getEmail());

        //check whether customer exists
        if( customer == null){

            throw new RuntimeException("Email is Invalid");

        }else if(signInCustomer.getPassword().equals(customer.getPassword())){
            CustomerSigned response=new CustomerSigned();
            response.setId(customer.getCustomerId());
            response.setEmail(customer.getEmail());
            response.setUsername(customer.getFirstName());

            return response;
        }else{

            throw new RuntimeException("Password is wrong. Try again");
        }

    }

    public List<Customer> getAll(){
        return authRepository.findAll();
    }

}
