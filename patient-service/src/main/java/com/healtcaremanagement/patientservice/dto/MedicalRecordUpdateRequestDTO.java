package com.healtcaremanagement.patientservice.dto;

import lombok.Data;

@Data
public class MedicalRecordUpdateRequestDTO {

    private String diagnosis;
    
    private String treatment;
    
    private String doctorName;
    
    private String visitDate;

    private String notes;
}
