package com.healtcaremanagement.patientservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.healtcaremanagement.patientservice.model.MedicalRecord;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, UUID> {

    public List<MedicalRecord> findByPatientId(UUID patientId);

    @Modifying
    @Query("DELETE FROM MedicalRecord m WHERE m.id = :id")
    public void deleteByIdDirect(@Param("id") UUID id);
}
