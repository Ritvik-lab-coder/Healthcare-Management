package com.healthcaremanagement.appointmentservice.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AppointmentRequestDTO {

    @NotBlank(message = "Subject is required")
    private String subject;
    
    @NotBlank(message = "Date is required")
    private LocalDate date;
    
    @NotBlank(message = "Time is required")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;
    
    @NotBlank(message = "Patient name is required")
    private String patientName;
}
