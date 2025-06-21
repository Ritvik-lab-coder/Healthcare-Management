package com.healtcaremanagement.patientservice.dto;

import lombok.Data;

@Data
public class VaccinationRecordUpdateRequestDTO {

    private String vaccineName;
    
    private String administeredBy;
    
    private String dateAdministered;

    private String notes;
}
