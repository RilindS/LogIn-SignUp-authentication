package com.example.LogIn.SignUp.Authentication.controller;



import com.example.LogIn.SignUp.Authentication.data.city.CreateCity;
import com.example.LogIn.SignUp.Authentication.service.CityService;
import com.example.LogIn.SignUp.Authentication.service.ResponseObject;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequestMapping("${base.url}/city")
@RestController
@AllArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping("/create")
    public ResponseEntity<CreateCity> createPatient(@RequestBody @Valid CreateCity createCity) {
        CreateCity createCity1 = cityService.createCity(createCity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createCity1);
    }

    @GetMapping("/all")
    public ResponseEntity getAll() {
        ResponseObject responseObject = cityService.getCity();
        return ResponseEntity.status(HttpStatus.OK).body(responseObject);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteCity(@PathVariable Long id) {
        return cityService.deleteCity(id);
    }

    @PutMapping("/update")
    public CreateCity updateCity(@RequestBody @Valid CreateCity createCity) {
        return cityService.updateCity(createCity);
    }

}
