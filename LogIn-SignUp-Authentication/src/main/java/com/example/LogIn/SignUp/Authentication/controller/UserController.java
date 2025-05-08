package com.example.LogIn.SignUp.Authentication.controller;

import com.example.LogIn.SignUp.Authentication.data.user.ForgotPasswordRequestDTO;
import com.example.LogIn.SignUp.Authentication.data.user.ResetPasswordRequestDTO;
import com.example.LogIn.SignUp.Authentication.security.auth.AuthenticationRequest;
import com.example.LogIn.SignUp.Authentication.security.auth.AuthenticationResponse;
import com.example.LogIn.SignUp.Authentication.security.auth.AuthenticationService;
import com.example.LogIn.SignUp.Authentication.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("${base.url}")
public class UserController {

    private final AuthenticationService service;
    private final UserService userService;

    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authentication(request));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody @Valid ForgotPasswordRequestDTO request) throws MessagingException, IOException {
        userService.sendPasswordResetCode(request.getEmail());
        return ResponseEntity.ok("Verification code sent to your email.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody @Valid ResetPasswordRequestDTO request) {
        userService.resetPassword(
                request.getEmail(),
                request.getVerificationCode(),
                request.getNewPassword(),
                request.getConfirmPassword()
        );
        return ResponseEntity.ok("Password changed successfully.");
    }

}
