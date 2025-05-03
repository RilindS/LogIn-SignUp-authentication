package com.example.LogIn.SignUp.Authentication.repository;

import com.example.LogIn.SignUp.Authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u from User u where u.email=:email and u.deletedAt is null ")
    Optional<User> findByEmail(@Param("email") String email);


    @Query("select u from User u where u.email =:email and u.deletedAt is null")
    Optional<User> findByUsername(@Param("email") String email);

}
