package com.healthcaremanagement.appointmentservice.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "appointments")
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String subject;
    
    @NotNull
    private LocalDate date;
    
    @NotNull
    private LocalTime time;
    
    @NotNull
    private String doctorId;

    @NotNull
    private String doctorName;
    
    @NotNull
    private String patientId;

    @NotNull
    private String patientName;
}
