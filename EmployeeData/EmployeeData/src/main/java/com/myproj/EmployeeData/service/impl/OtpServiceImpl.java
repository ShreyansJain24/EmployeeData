package com.myproj.EmployeeData.service.impl;

import com.myproj.EmployeeData.entities.OtpDetails;
import com.myproj.EmployeeData.repositories.OtpRepository;
import com.myproj.EmployeeData.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

@Service
public class OtpServiceImpl implements OtpService {
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private OtpRepository otpRepository;

    @Override
    public boolean sendOtp(String mail) {
        String generatedOTP = generateRandomOTP();

        OtpDetails myOtp= new OtpDetails();
        myOtp.setMail(mail);
        myOtp.setOtp(generatedOTP);
        myOtp.setExpiryTime(calculateExpiryTime());

        otpRepository.save(myOtp);

        //save otp in database for verification
        return sendOTPEmail(mail, generatedOTP);
    }

    @Override
    public String verifyOtp(String mail, String otp) {
        // Find OTP details associated with the user
        OtpDetails otpDetails = otpRepository.findByMail(mail);

        // Check if OTP exists and hasn't expired
        if(!Objects.isNull(otpDetails)){
            if(!isExpired(otpDetails.getExpiryTime())){
                if(otpDetails.getOtp().equals(otp)){
                    otpRepository.delete(otpDetails);
                    return "Otp Verified";
                }else{
                    return "Otp Invalid";
                }
            }else{
                otpRepository.delete(otpDetails);
                return "Otp Expired";
            }
        }
        return "Otp Not Found";
    }


    private String generateRandomOTP() {
        Random random = new Random();
        int otp = 100_000 + random.nextInt(900_000);
        return String.valueOf(otp);
    }

    private boolean sendOTPEmail(String userEmail, String otp) {
        boolean sentStatus=false;
        // Create and send an email with the OTP
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("4999shreyansjain@gmail.com");
        message.setTo(userEmail);
        message.setSubject("Login OTP");
        message.setText("Your OTP for Login is: " + otp);
        try{
            emailSender.send(message);
            sentStatus=true;
        }catch (Exception e){
            sentStatus=false;
        }
        return sentStatus;
    }


    private Date calculateExpiryTime() {
        // 2 minutes in milliseconds
        return new Date(System.currentTimeMillis() + 2 * 60 * 1000);
    }

    private boolean isExpired(Date expiryTime) {
        return expiryTime != null && expiryTime.before(new Date());
    }
}
