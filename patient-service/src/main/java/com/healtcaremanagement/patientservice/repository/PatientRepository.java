package com.healtcaremanagement.patientservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healtcaremanagement.patientservice.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, UUID> {

}
