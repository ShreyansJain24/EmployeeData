package com.myproj.EmployeeData.controller;

import com.myproj.EmployeeData.entities.EmployeeDetails;
import com.myproj.EmployeeData.service.EmployeeService;
import com.myproj.EmployeeData.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(value = "/otp")
public class OtpController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    OtpService otpService;

    @GetMapping(value = "/send-otp")
    public String sendOtp(@RequestParam("email") String email,@RequestParam("password") String password){
        EmployeeDetails emp= employeeService.findUserByMail(email);
        if(Objects.isNull(emp)){
            return "no employee found with given mail";
        }else{
            if(emp.getPassword().equals(password)){
                String msg=otpService.sendOtp(email)?"otp sent "
                        :"failure while sending otp";
              return msg;
            }else{
            return "incorrect password";
            }
        }
    }

    @GetMapping(value = "/verify-otp")
    public String verifyOtp(@RequestParam("email") String email,@RequestParam("otp") String otp){
        return otpService.verifyOtp(email,otp);
    }

}
