package com.example.LogIn.SignUp.Authentication.repository;

import com.example.LogIn.SignUp.Authentication.data.user.ViewUser1;
import com.example.LogIn.SignUp.Authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u from User u where u.email=:email and u.deletedAt is null ")
    Optional<User> findByEmail(@Param("email") String email);


    @Query("select u from User u where u.email =:email and u.deletedAt is null")
    Optional<User> findByUsername(@Param("email") String email);

    @Query("SELECT new com.example.LogIn.SignUp.Authentication.data.user.ViewUser1(" +
            "u.id, u.firstName, u.lastName, u.email, u.phoneNumber, u.imageUrl, u.status, u.twoFactorEnabled, u.city.name)" +
            " FROM User u LEFT JOIN u.city where u.deletedAt is null")
    List<ViewUser1> getAllUsers();

}
