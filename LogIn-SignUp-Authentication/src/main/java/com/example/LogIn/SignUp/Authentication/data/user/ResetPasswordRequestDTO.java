package com.example.LogIn.SignUp.Authentication.data.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResetPasswordRequestDTO {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String verificationCode;

    @NotBlank
    private String newPassword;

    @NotBlank
    private String confirmPassword;
}
