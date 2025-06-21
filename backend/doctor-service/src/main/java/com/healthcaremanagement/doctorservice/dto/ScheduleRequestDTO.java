package com.healthcaremanagement.doctorservice.dto;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ScheduleRequestDTO {

    @NotBlank(message = "Day of the week cannot be blank")
    private String dayOfWeek;

    @NotBlank(message = "Start time cannot be blank")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;
    
    @NotBlank(message = "End time cannot be blank")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

    @NotBlank(message = "Availability status cannot be blank")
    private boolean isAvailable;
}
