package com.example.LogIn.SignUp.Authentication.data.user;

import com.example.LogIn.SignUp.Authentication.enums.Status;
import lombok.*;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
public class ViewUser {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String imageUrl;
    private Status status;
}