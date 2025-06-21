package com.healthcaremanagement.doctorservice.dto;

import lombok.Data;

@Data
public class ScheduleResponseDTO {

    private String doctorName;

    private String dayOfWeek;

    private String startTime;

    private String endTime;
    
    private boolean isAvailable;
}
