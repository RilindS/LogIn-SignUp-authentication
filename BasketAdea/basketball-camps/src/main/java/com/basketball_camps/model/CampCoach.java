package com.basketball_camps.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class CampCoach extends UserAccount{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camp_id", nullable = false)
    private Camp camp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coach_id", nullable = false)
    private Coach coach;

    @NotNull
    @Column(nullable = false)
    private LocalDate assignedDate;   // kur ju ka  bashku kampit

    @NotEmpty
    @Column(nullable = false)
    private String role; // p.sh. Kryetrajner, Asistent, Specialist etj.
}
