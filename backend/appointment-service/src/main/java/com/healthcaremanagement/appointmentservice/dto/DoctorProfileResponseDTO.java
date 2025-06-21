package com.healthcaremanagement.appointmentservice.dto;

import java.util.List;

import lombok.Data;

@Data
public class DoctorProfileResponseDTO {

    private String id;

    private Integer age;

    private Integer experience;

    private String specialization;

    private List<String> availableDays;

    private String createdAt;

    private String name;

    private String email;

    private String address;

    private String contact;

    private String gender;

    private String dateOfBirth;
}
