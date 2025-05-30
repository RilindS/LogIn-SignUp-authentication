package com.example.LogIn.SignUp.Authentication.controller;

import com.example.LogIn.SignUp.Authentication.data.user.*;
import com.example.LogIn.SignUp.Authentication.security.auth.AuthenticationRequest;
import com.example.LogIn.SignUp.Authentication.security.auth.AuthenticationResponse;
import com.example.LogIn.SignUp.Authentication.security.auth.AuthenticationService;
import com.example.LogIn.SignUp.Authentication.security.auth.VerifyOtpRequest;
import com.example.LogIn.SignUp.Authentication.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("${base.url}")
@Log4j2

public class UserController {

    private final AuthenticationService service;
    private final UserService userService;

    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ) throws MessagingException, IOException {
        return ResponseEntity.ok(service.authentication(request));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<AuthenticationResponse> verifyOtp(@RequestBody VerifyOtpRequest request) {
        return ResponseEntity.ok(service.verifyOtp(request));
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

    @PutMapping("/user/{id}/2fa")
    public ResponseEntity<String> updateTwoFactor(
            @PathVariable Long id,
            @RequestParam boolean enabled
    ) {
        userService.updateTwoFactorEnabled(id, enabled);
        return ResponseEntity.ok("Two-Factor Authentication " + (enabled ? "enabled" : "disabled") + " successfully.");
    }

    @PostMapping("/users/create")
    public ResponseEntity<AuthenticationResponse> createUser(
            @RequestBody RegisterRequest request
    ) throws MessagingException, IOException {
        return ResponseEntity.ok(service.createUser(request));
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<ViewUser1>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @PutMapping("users/{id}")
    public ResponseEntity<ViewUser> editUser(@PathVariable Long id, @RequestBody EditUserRequest request) {
        return ResponseEntity.ok(userService.editUser(id, request));
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("users/me")
    public ResponseEntity<ViewUser> getMyProfile(@AuthenticationPrincipal UserDetails userDetails) {
        String methodName = "getMyProfile";

        log.info("{} -> Get My Profile", methodName);
        ViewUser userView = userService.myProfile(userDetails);

        log.info("{} -> Get user, response status: 200", methodName);

        return ResponseEntity.status(HttpStatus.OK).body(userView);
    }
}
