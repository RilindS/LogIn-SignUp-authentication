package com.example.LogIn.SignUp.Authentication.repository;

import com.example.LogIn.SignUp.Authentication.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
