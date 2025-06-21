package com.healtcaremanagement.patientservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PatientRequestDTO {

    @NotBlank(message = "Age is required")
    private Integer age;
    
    @NotBlank(message = "Blood group is required")
    private String bloodGroup;
    
    @NotBlank(message = "Emergency contact is required")
    private String emergencyContact;

    private String allergies;
}
