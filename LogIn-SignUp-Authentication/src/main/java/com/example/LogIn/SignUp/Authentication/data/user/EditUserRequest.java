package com.example.LogIn.SignUp.Authentication.data.user;

import lombok.Data;

@Data
public class EditUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String imageUrl;
    private Long cityId;
}