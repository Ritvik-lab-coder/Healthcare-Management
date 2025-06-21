package com.healthcaremanagement.doctorservice.mapper;

import com.healthcaremanagement.doctorservice.dto.ScheduleRequestDTO;
import com.healthcaremanagement.doctorservice.dto.ScheduleResponseDTO;
import com.healthcaremanagement.doctorservice.model.Doctor;
import com.healthcaremanagement.doctorservice.model.Schedule;

public class ScheduleMapper {

    public static ScheduleResponseDTO toDTO(Schedule schedule, String doctorName) {

        ScheduleResponseDTO scheduleResponseDTO = new ScheduleResponseDTO();

        scheduleResponseDTO.setDoctorName(doctorName);
        scheduleResponseDTO.setDayOfWeek(schedule.getDayOfWeek());
        scheduleResponseDTO.setStartTime(schedule.getStartTime().toString());
        scheduleResponseDTO.setEndTime(schedule.getEndTime().toString());
        scheduleResponseDTO.setAvailable(schedule.isAvailable());

        return scheduleResponseDTO;
    }

    public static Schedule toModel(ScheduleRequestDTO scheduleRequestDTO, Doctor doctor) {

        Schedule schedule = new Schedule();

        schedule.setDoctor(doctor);
        schedule.setDayOfWeek(scheduleRequestDTO.getDayOfWeek());
        schedule.setStartTime(scheduleRequestDTO.getStartTime());
        schedule.setEndTime(scheduleRequestDTO.getEndTime());
        schedule.setAvailable(scheduleRequestDTO.isAvailable());

        return schedule;
    }
}
