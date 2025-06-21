package com.healtcaremanagement.patientservice.dto;

import lombok.Data;

@Data
public class PatientProfileResponseDTO {

    private String id;

    private String name;

    private String email;

    private String gender;

    private String dateOfBirth;

    private String contact;

    private String address;

    private String role;

    private Integer age;

    private String emergencyContact;

    private String allergies;

    private String bloodGroup;
}
