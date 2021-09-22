package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.dto.request.CustomerSignInRequest;
import com.alpha5.autoaid.dto.request.CustomerSignUpRequest;
import com.alpha5.autoaid.dto.request.Resetpwd;
import com.alpha5.autoaid.dto.response.UserSigned;
import com.alpha5.autoaid.model.Customer;
import com.alpha5.autoaid.service.AuthService;
import com.alpha5.autoaid.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody CustomerSignUpRequest customerSignUpRequest){

        String email=customerSignUpRequest.getEmail();
        String username=customerSignUpRequest.getUserName();
        String contactNo=customerSignUpRequest.getContactNo();
        String responseMsg;
        if (authService.checkIfEmailExists(email)){
            responseMsg="Email exists";
        }else if (authService.checkIfContactExists(contactNo)){
            responseMsg="Contact exists";
        }else if (authService.checkIfUserNameExists(username)){
            responseMsg="username exists";
        }else {
            authService.signup(customerSignUpRequest);
            responseMsg="Customer Added Successfully";
            return ResponseEntity.ok().body(responseMsg);
        }
        return ResponseEntity.badRequest().body(responseMsg);
    }

    @PostMapping("/user/login")
    public ResponseEntity userLogin(@RequestBody CustomerSignInRequest customerSignInRequest){
        //get object of relavant user
        String email=customerSignInRequest.getEmail();
        String userName=customerSignInRequest.getUserName();
        String responseMsg;
        //continue if user exists on provided details
        if (authService.findbyUserNameorEmail(userName,email)){
            UserSigned response= authService.userLogin(customerSignInRequest);
            return ResponseEntity.ok().body(response);
        }
        responseMsg="UserName or email Invalid";
        return ResponseEntity.badRequest().body(responseMsg);
    }

    @PostMapping("/gettoken")
    public String generateToken(@RequestBody CustomerSignInRequest signInRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword())
            );
        }catch (Exception ex){
            throw new Exception("Invalid Username or password");
        }
        return jwtTokenUtil.generateToken(signInRequest.getEmail());
    }

    @GetMapping("/allcustomers")
    public List<Customer> findAllCustomers(){
        return authService.getAll();
    }

    @PostMapping("/forgetpwd")
    public ResponseEntity forgetPwd(@RequestBody Resetpwd resetpwd){

        String email=resetpwd.getEmail();
        String newpwd=resetpwd.getNewpwd();
        String responseMsg ="";
        if(authService.resetpwd(email,newpwd)){
            responseMsg="Successful!";
            return ResponseEntity.ok().body(responseMsg);
        }else{
            responseMsg="Error!";
            return ResponseEntity.badRequest().body(responseMsg);
        }

//        String responseMsg;
//        if (authService.checkIfEmailExists(email)){
//            responseMsg="Email exists";
//        }else if (authService.checkIfContactExists(contactNo)){
//            responseMsg="Contact exists";
//        }else if (authService.checkIfUserNameExists(username)){
//            responseMsg="username exists";
//        }else {
//            authService.signup(customerSignUpRequest);
//            responseMsg="Customer Added Successfully";
//        }
    }

}
