package com.healthcaremanagement.authservice.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {

    private UserResponseDTO userResponseDTO;

    private String token;
}
