package com.healthcaremanagement.appointmentservice.dto;

import lombok.Data;

@Data
public class AppointmentResponseDTO {

    private String id;

    private String subject;

    private String date;

    private String time;

    private String doctorId;

    private String doctorName;

    private String patientId;

    private String patientName;
}
