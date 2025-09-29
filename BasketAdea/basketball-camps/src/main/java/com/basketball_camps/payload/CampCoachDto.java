package com.basketball_camps.payload;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CampCoachDto {
    private Long campId;
    private Long coachId;
    private LocalDate assignedDate;
    private String role;
}