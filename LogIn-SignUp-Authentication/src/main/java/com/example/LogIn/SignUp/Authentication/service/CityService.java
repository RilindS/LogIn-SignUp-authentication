package com.example.LogIn.SignUp.Authentication.service;

import com.example.LogIn.SignUp.Authentication.data.city.CreateCity;
import com.example.LogIn.SignUp.Authentication.entity.City;
import com.example.LogIn.SignUp.Authentication.repository.CityRepository;
import org.springframework.stereotype.Service;
import com.example.LogIn.SignUp.Authentication.data.city.ViewCity;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
    public CreateCity createCity(CreateCity createCity) {
        City city = new City();
        if (createCity != null) {

            city.setName(createCity.getName());
            cityRepository.save(city);
        }
        return createCity;
    }

    public ResponseObject getCity() {
        ResponseObject responseObject = new ResponseObject();
        List<ViewCity> nurses=cityRepository.viewAllCity();
        responseObject.setData(nurses);
        return responseObject;
    }

    public Boolean deleteCity(Long id) {
        City city = cityRepository.findById(id).orElseThrow(()-> new RuntimeException("City Not Found"));
        city.setDeletedAt(LocalDateTime.now());
        cityRepository.save(city);
        return true;
    }

    public CreateCity updateCity(CreateCity createCity) {
        City city = cityRepository.findById(createCity.getId()).orElseThrow(()-> new RuntimeException("City Not Found"));

        city.setName(createCity.getName());

        cityRepository.save(city);
        return createCity;
    }


}
