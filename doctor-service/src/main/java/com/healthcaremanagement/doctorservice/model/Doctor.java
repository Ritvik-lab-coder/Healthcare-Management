package com.healthcaremanagement.doctorservice.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "doctors")
@Data
public class Doctor {

    @Id
    private UUID id;

    @NotNull
    private Integer age;
    
    @NotNull
    private String specialization;
    
    @NotNull
    private Integer experience;

    @NotNull
    private List<String> availableDays;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schedule> schedules;

    @NotNull
    @CreationTimestamp
    private LocalDate createdAt;
}
