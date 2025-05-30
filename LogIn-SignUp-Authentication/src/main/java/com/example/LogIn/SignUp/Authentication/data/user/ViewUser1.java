package com.example.LogIn.SignUp.Authentication.data.user;

import com.example.LogIn.SignUp.Authentication.entity.City;
import com.example.LogIn.SignUp.Authentication.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class ViewUser1 {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String imageUrl;
    private Status status;
    private Boolean twoFactorEnabled;
    private String cityName;
}
