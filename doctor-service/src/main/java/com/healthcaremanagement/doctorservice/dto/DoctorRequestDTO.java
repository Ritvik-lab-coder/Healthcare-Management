package com.healthcaremanagement.doctorservice.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DoctorRequestDTO {

    @NotBlank(message = "Age is required")
    private Integer age;
    
    @NotBlank(message = "Experience is required")
    private Integer experience;
    
    @NotBlank(message = "Specialization is required")
    private String specialization;
    
    @NotBlank(message = "Available days is required")
    private List<String> availableDays;
}
