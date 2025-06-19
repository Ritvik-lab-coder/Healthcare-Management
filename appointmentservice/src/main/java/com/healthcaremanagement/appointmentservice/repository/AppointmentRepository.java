package com.healthcaremanagement.appointmentservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcaremanagement.appointmentservice.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    public List<Appointment> findByDoctorId(String id);

    public List<Appointment> findByPatientId(String id);
}
