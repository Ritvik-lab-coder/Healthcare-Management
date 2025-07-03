package com.healtcaremanagement.patientservice.dto;

import lombok.Data;

@Data
public class UserResponseDTO {

    private String id;

    private String name;

    private String email;

    private String gender;

    private String dateOfBirth;

    private String contact;

    private Boolean isProfileComplete;

    private String address;

    private String role;
}
