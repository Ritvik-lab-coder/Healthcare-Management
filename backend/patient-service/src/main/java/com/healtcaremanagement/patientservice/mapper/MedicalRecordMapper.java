package com.healtcaremanagement.patientservice.mapper;

import java.time.LocalDate;

import com.healtcaremanagement.patientservice.dto.MedicalRecordRequestDTO;
import com.healtcaremanagement.patientservice.dto.MedicalRecordResponseDTO;
import com.healtcaremanagement.patientservice.model.MedicalRecord;

public class MedicalRecordMapper {

    public static MedicalRecordResponseDTO toDTO(MedicalRecord medicalRecord) {

        MedicalRecordResponseDTO medicalRecordResponseDTO = new MedicalRecordResponseDTO();

        medicalRecordResponseDTO.setId(medicalRecord.getId().toString());
        medicalRecordResponseDTO.setDiagnosis(medicalRecord.getDiagnosis());
        medicalRecordResponseDTO.setDoctorName(medicalRecord.getDoctorName());
        medicalRecordResponseDTO.setTreatment(medicalRecord.getTreatment());
        medicalRecordResponseDTO.setVisitDate(medicalRecord.getVisitDate().toString());
        medicalRecordResponseDTO.setNotes(medicalRecord.getNotes());

        return medicalRecordResponseDTO;
    }

    public static MedicalRecord toModel(MedicalRecordRequestDTO medicalRecordRequestDTO) {

        MedicalRecord medicalRecord = new MedicalRecord();

        medicalRecord.setDiagnosis(medicalRecordRequestDTO.getDiagnosis());
        medicalRecord.setDoctorName(medicalRecordRequestDTO.getDoctorName());
        medicalRecord.setTreatment(medicalRecordRequestDTO.getTreatment());
        medicalRecord.setVisitDate(LocalDate.parse(medicalRecordRequestDTO.getVisitDate()));

        if (medicalRecordRequestDTO.getNotes() != null)
            medicalRecord.setNotes(medicalRecordRequestDTO.getNotes());

        return medicalRecord;
    }
}
