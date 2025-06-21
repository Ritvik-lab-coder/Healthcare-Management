package com.healthcaremanagement.doctorservice.dto;

import java.util.List;

import lombok.Data;

@Data
public class UpdateDoctorDetailsRequestDTO {
    
    private Integer age;
    
    private Integer experience;
    
    private String specialization;
    
    private List<String> availableDays;
}
