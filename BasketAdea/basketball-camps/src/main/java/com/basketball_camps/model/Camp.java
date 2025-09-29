package com.basketball_camps.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Camp extends BaseEntity {

   @NotEmpty
   @Column(nullable = false)
   private String name;

   @NotEmpty
   @Column(columnDefinition = "TEXT")
   private String description;

   @NotNull
   @Column(nullable = false)
   private LocalDate startDate;

   @NotNull
   @Column(nullable = false)
   private LocalDate endDate;

   @NotNull
   @DecimalMin(value = "0.0", inclusive = false)
   @Column(nullable = false, precision = 5, scale = 2)
   private BigDecimal price;

   @NotNull
   @Column(nullable = false)
   private Integer maxParticipants;

   @Column(nullable = false)
   private Integer currentParticipants = 0;

   @NotEmpty
   @Column(nullable = false)
   private String location;

   //tash kjo ma svyn nashtaj se i kem many to many lidhjen tjeter
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "coach_id")
   private Coach coach;

   @OneToMany(mappedBy = "camp", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<CampCoach> campCoaches = new ArrayList<>();


   @Column(nullable = false)
   private Boolean isActive;

   @Column(nullable = false)
   private LocalDateTime registrationDeadline;

   public boolean isRegistrationOpen() {
      return isActive && LocalDateTime.now().isBefore(registrationDeadline) &&
            currentParticipants < maxParticipants;
   }

   public void incrementParticipants() {
      this.currentParticipants++;
   }

   public void decrementParticipants() {
      if (this.currentParticipants > 0) {
         this.currentParticipants--;
      }
   }
}