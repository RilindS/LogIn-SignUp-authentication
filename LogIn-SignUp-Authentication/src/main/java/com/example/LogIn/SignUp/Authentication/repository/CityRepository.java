package com.example.LogIn.SignUp.Authentication.repository;

import com.example.LogIn.SignUp.Authentication.data.city.ViewCity;
import com.example.LogIn.SignUp.Authentication.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query("select new com.example.LogIn.SignUp.Authentication.data.city.ViewCity(c.id,c.name)" +
            "from City c " +
            "where c.deletedAt is null ")
    List<ViewCity> viewAllCity();
}
