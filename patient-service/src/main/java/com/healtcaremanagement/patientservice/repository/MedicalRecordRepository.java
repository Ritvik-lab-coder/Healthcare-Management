package com.healtcaremanagement.patientservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healtcaremanagement.patientservice.model.MedicalRecord;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, UUID> {

    public List<MedicalRecord> findByPatientId(UUID patientId);
}
