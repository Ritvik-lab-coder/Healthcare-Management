package com.healtcaremanagement.patientservice.mapper;

import java.time.LocalDate;

import com.healtcaremanagement.patientservice.dto.VaccinationRecordRequestDTO;
import com.healtcaremanagement.patientservice.dto.VaccinationRecordResponseDTO;
import com.healtcaremanagement.patientservice.model.VaccinationRecord;

public class VaccinationRecordMapper {

    public static VaccinationRecordResponseDTO toDTO(VaccinationRecord vaccinationRecord) {

        VaccinationRecordResponseDTO vaccinationRecordResponseDTO = new VaccinationRecordResponseDTO();

        vaccinationRecordResponseDTO.setId(vaccinationRecord.getId().toString());
        vaccinationRecordResponseDTO.setVaccineName(vaccinationRecord.getVaccineName());
        vaccinationRecordResponseDTO.setAdministeredBy(vaccinationRecord.getAdministeredBy());
        vaccinationRecordResponseDTO.setDateAdministered(vaccinationRecord.getDateAdministered().toString());
        vaccinationRecordResponseDTO.setNotes(vaccinationRecord.getNotes());

        return vaccinationRecordResponseDTO;
    }

    public static VaccinationRecord toModel(VaccinationRecordRequestDTO vaccinationRecordRequestDTO) {

        VaccinationRecord vaccinationRecord = new VaccinationRecord();

        vaccinationRecord.setVaccineName(vaccinationRecordRequestDTO.getVaccineName());
        vaccinationRecord.setAdministeredBy(vaccinationRecordRequestDTO.getAdministeredBy());
        vaccinationRecord.setDateAdministered(LocalDate.parse(vaccinationRecordRequestDTO.getDateAdministered()));

        if (vaccinationRecordRequestDTO.getNotes() != null)
            vaccinationRecord.setNotes(vaccinationRecordRequestDTO.getNotes());

        return vaccinationRecord;
    }
}
