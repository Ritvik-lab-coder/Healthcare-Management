package com.healthcaremanagement.authservice.dto;

import lombok.Data;

@Data
public class UpdateUserProfileRequestDTO {

    private String name;

    private String email;

    private String password;

    private String dateOfBirth;

    private String contact;

    private String address;

    private String gender;
}
