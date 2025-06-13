package com.healthcaremanagement.doctorservice.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReviewRequestDTO {

    @NotBlank(message = "Rating is required")
    private Integer rating;

    private String comment;

    @NotBlank(message = "Timestamp is required")
    private LocalDate createdAt;
}
