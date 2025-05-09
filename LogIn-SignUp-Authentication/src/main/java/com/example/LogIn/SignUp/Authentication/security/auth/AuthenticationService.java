package com.example.LogIn.SignUp.Authentication.security.auth;

import com.example.LogIn.SignUp.Authentication.repository.UserRepository;
import com.example.LogIn.SignUp.Authentication.security.service.JwtService;
import com.example.LogIn.SignUp.Authentication.service.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthenticationResponse authentication(AuthenticationRequest request) throws MessagingException, IOException {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        if (user.isTwoFactorEnabled()) {
            String otpCode = String.valueOf(new Random().nextInt(900000) + 100000);
            user.setTwoFactorCode(otpCode);
            user.setTwoFactorExpiry(LocalDateTime.now().plusMinutes(10));
            userRepository.save(user);

            userService.sendTwoFactorCode(user.getEmail(), otpCode);

            return AuthenticationResponse.builder()
                    .twoFactorRequired(true)
                    .userId(user.getId())
                    .message("OTP sent to your email")
                    .build();
        }

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userId(user.getId())
                .twoFactorRequired(false)
                .build();
       // return AuthenticationResponse.builder().token(jwtToken).userId(user.getId()).build();
    }
    public AuthenticationResponse verifyOtp(VerifyOtpRequest request) {
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!user.getTwoFactorCode().equals(request.getOtp())
                || user.getTwoFactorExpiry().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Invalid or expired OTP");
        }

        user.setTwoFactorCode(null);
        user.setTwoFactorExpiry(null);
        userRepository.save(user);

        var jwt = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwt)
                .userId(user.getId())
                .build();
    }

}