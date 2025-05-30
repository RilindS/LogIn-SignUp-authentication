package com.example.LogIn.SignUp.Authentication.data.user;

import com.example.LogIn.SignUp.Authentication.entity.City;
import com.example.LogIn.SignUp.Authentication.entity.User;
import com.example.LogIn.SignUp.Authentication.enums.Status;
import com.example.LogIn.SignUp.Authentication.repository.CityRepository;
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
    private Boolean twoFactorEnabled;
    private City cityName;
    public ViewUser(Long id, LocalDateTime createdAt, String createdBy, String firstName, String lastName, String email, String phoneNumber, Status status, String name, Boolean twoFactorEnabled, City cityName) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.status = status;
            this.imageUrl = name;
            this.twoFactorEnabled = twoFactorEnabled;
            this.cityName = cityName;
    }

    public static ViewUser fromEntity(User user) {
        return new ViewUser(
                user.getId(),
                user.getCreatedAt(),
                user.getCreatedBy(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getStatus(),
                user.getRole().getName(),
                user.getTwoFactorEnabled(),
                user.getCity()
        );
    }
}