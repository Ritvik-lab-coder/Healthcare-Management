package com.healtcaremanagement.patientservice.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.healtcaremanagement.patientservice.enums.BloodGroup;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "patients")
@Data
public class Patient {

    @Id
    private UUID id;

    @NotNull
    private Integer age;

    @NotNull
    private LocalDate registeredAt = LocalDate.now();

    @NotNull
    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    @NotNull
    private String emergencyContact;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)  
    private List<MedicalRecord> medicalRecords;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY) 
    private List<VaccinationRecord> vaccinationRecords;

    private String allergies;
}
