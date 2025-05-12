package com.example.LogIn.SignUp.Authentication.data.user;


import com.example.LogIn.SignUp.Authentication.enums.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;

    @NotBlank(message = "must.not.be.empty")
    @Email(message = "email.is.not.valid")
    private String email;

    @Size(min = 5, max = 45, message = "password.min.max")
    private String password;

    private String phoneNumber;
    private String imageUrl;
    // private Status status;
    private Entity role;
}
