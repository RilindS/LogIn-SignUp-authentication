package com.example.LogIn.SignUp.Authentication.controller;

import com.example.LogIn.SignUp.Authentication.security.auth.AuthenticationRequest;
import com.example.LogIn.SignUp.Authentication.security.auth.AuthenticationResponse;
import com.example.LogIn.SignUp.Authentication.security.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("${base.url}")
public class UserController {

    private final AuthenticationService service;

    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authentication(request));
    }
}
