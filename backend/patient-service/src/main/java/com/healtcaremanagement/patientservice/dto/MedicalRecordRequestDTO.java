package com.healtcaremanagement.patientservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MedicalRecordRequestDTO {

    @NotBlank(message = "Diagnosis is required")
    private String diagnosis;
    
    @NotBlank(message = "Treatment is required")
    private String treatment;
    
    @NotBlank(message = "Doctor name is required")
    private String doctorName;
    
    @NotBlank(message = "Visit date is required")
    private String visitDate;

    private String notes;
}
