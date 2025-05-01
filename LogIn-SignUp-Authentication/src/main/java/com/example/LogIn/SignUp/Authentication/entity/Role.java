package com.example.LogIn.SignUp.Authentication.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Column(unique = true,nullable = false)
    private String name;

//    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
//    private List<RolePermissions> rolePermissions;

}
