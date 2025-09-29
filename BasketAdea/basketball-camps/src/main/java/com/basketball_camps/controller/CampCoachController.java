package com.basketball_camps.controller;

import com.basketball_camps.model.CampCoach;
import com.basketball_camps.payload.CampCoachDto;
import com.basketball_camps.service.CampCoachService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/camp-coaches")
@RequiredArgsConstructor
public class CampCoachController {

    private final CampCoachService service;

    @PostMapping
    public CampCoach assignCoachToCamp(@RequestBody CampCoachDto dto) {
        return service.assignCoachToCamp(dto);
    }
}



