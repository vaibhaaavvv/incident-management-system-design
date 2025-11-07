package com.triggr.service;

import com.triggr.dto.SignupReq;
import com.triggr.model.SignupRequest;
import com.triggr.repository.SignupRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private SignupRequestRepository signupRequestRepository;

    public String signup(SignupReq request) {
        String otp = generateOtp();
        SignupRequest entity = new SignupRequest(request.getEmail(), otp);
        signupRequestRepository.save(entity);
        return "Signup request created with OTP: " + otp;
    }

    private String generateOtp() {
        return String.format("%06d", new Random().nextInt(1000000));
    }
}