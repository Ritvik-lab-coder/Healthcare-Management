package com.healthcaremanagement.doctorservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcaremanagement.doctorservice.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

    public List<Doctor> findBySpecialization(String specialization);
}
