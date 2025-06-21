package com.healthcaremanagement.appointmentservice.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BillRequestDTO {

    @NotBlank(message = "Description is required")
    private String description;
    
    @NotBlank(message = "Due date is required")
    private LocalDate dueDate;
    
    @NotBlank(message = "Amount is required")
    private float amount;
}
