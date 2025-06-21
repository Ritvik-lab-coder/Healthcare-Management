package com.healthcaremanagement.doctorservice.dto;

import lombok.Data;

@Data
public class ScheduleUpdateRequestDTO {

    private String dayOfWeek;

    private String startTime;

    private String endTime;

    private String isAvailable;
}
