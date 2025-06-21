package com.healtcaremanagement.patientservice.dto;

import lombok.Data;

@Data
public class PatientResponseDTO {

    private String id;

    private Integer age;

    private String registeredAt;

    private String bloodGroup;

    private String emergencyContact;

    private String allergies;
}
