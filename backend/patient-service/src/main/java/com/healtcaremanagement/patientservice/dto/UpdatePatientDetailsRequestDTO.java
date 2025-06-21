package com.healtcaremanagement.patientservice.dto;

import lombok.Data;

@Data
public class UpdatePatientDetailsRequestDTO {

    private Integer age;

    private String bloodGroup;

    private String emergencyContact;

    private String allergies;
}
