package com.healtcaremanagement.patientservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.healtcaremanagement.patientservice.model.VaccinationRecord;

public interface VaccinationRecordRepository extends JpaRepository<VaccinationRecord, UUID> {

    public List<VaccinationRecord> findByPatientId(UUID patientId);

    @Modifying
    @Query("DELETE FROM VaccinationRecord v WHERE v.id = :id")
    public void deleteByIdDirect(@Param("id") UUID id);

}
