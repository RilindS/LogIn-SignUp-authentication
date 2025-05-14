package com.example.LogIn.SignUp.Authentication.entity;

import com.example.LogIn.SignUp.Authentication.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "reset_password_token")
    private String resetPassword;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "guid")
    private String guid;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder.Default
    @Column(name = "two_factor_enabled")
    private Boolean twoFactorEnabled=false;

    @Column(name = "two_factor_code")
    private String twoFactorCode;

    @Column(name = "two_factor_expiry")
    private LocalDateTime twoFactorExpiry;


    @ManyToOne(targetEntity = Role.class, fetch = FetchType.EAGER)
    private Role role;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public boolean isTwoFactorEnabled() {
        return twoFactorEnabled;
    }
}