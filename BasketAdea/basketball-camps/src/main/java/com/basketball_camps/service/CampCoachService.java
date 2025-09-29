package com.basketball_camps.service;

import com.basketball_camps.model.Camp;
import com.basketball_camps.model.CampCoach;
import com.basketball_camps.model.Coach;
import com.basketball_camps.payload.CampCoachDto;
import com.basketball_camps.repository.CampCoachRepository;
import com.basketball_camps.repository.CampRepository;
import com.basketball_camps.repository.CoachRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CampCoachService {

    private final CampCoachRepository repository;
    private final CampRepository campRepository;
    private final CoachRepository coachRepository;

    @Transactional
    public CampCoach assignCoachToCamp(CampCoachDto dto) {
        Camp camp = campRepository.findById(dto.getCampId())
                .orElseThrow(() -> new RuntimeException("Camp not found"));

        Coach coach = coachRepository.findById(dto.getCoachId())
                .orElseThrow(() -> new RuntimeException("Coach not found"));

        CampCoach campCoach = new CampCoach();
        campCoach.setCamp(camp);
        campCoach.setCoach(coach);
        campCoach.setAssignedDate(dto.getAssignedDate());
        campCoach.setRole(dto.getRole());

        return repository.save(campCoach);
    }
}
