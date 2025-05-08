package com.example.LogIn.SignUp.Authentication.data.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ForgotPasswordRequestDTO {

    @Email
    @NotBlank
    private String email;
}