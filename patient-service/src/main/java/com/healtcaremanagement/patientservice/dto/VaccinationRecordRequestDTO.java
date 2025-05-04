package com.healtcaremanagement.patientservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VaccinationRecordRequestDTO {

    @NotBlank(message = "Vaccine name is required")
    private String vaccineName;
    
    @NotBlank(message = "Administrator name is required")
    private String administeredBy;
    
    @NotBlank(message = "Administration date is required")
    private String dateAdministered;

    private String notes;
}
