package com.example.LogIn.SignUp.Authentication.security.auth;

import lombok.Data;

@Data
public class VerifyOtpRequest {
    private String email;
    private String otp;
}