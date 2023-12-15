package com.myproj.EmployeeData.service;

public interface OtpService {
    boolean sendOtp(String mail);
    String verifyOtp(String mail,String otp);
}
