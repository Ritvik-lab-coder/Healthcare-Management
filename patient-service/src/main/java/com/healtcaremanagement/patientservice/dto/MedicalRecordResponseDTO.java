package com.healtcaremanagement.patientservice.dto;

import lombok.Data;

@Data
public class MedicalRecordResponseDTO {

    private String id;

    private String diagnosis;

    private String treatment;

    private String doctorName;

    private String visitDate;

    private String notes;
}
