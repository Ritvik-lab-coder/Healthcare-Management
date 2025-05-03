package com.healtcaremanagement.patientservice.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "vaccination_records")
@Data
public class VaccinationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "patient_id", nullable = false)  
    private Patient patient;

    @NotNull
    private String vaccineName;

    @NotNull
    private String administeredBy;

    @NotNull
    private LocalDate dateAdministered;

    private String notes;
}
