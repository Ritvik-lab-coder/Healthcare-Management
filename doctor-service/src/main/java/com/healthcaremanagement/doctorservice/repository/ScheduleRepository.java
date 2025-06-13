package com.healthcaremanagement.doctorservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcaremanagement.doctorservice.model.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {

    public List<Schedule> findByDoctorId(UUID doctorId);
}
