package com.healthcaremanagement.doctorservice.dto;

import lombok.Data;

@Data
public class ReviewResponseDTO {

    private String doctorName;

    private String patientName;

    private Integer rating;
    
    private String comment;
}
