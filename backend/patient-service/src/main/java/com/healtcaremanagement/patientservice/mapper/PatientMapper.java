package com.healtcaremanagement.patientservice.mapper;

import java.time.LocalDate;

import com.healtcaremanagement.patientservice.dto.PatientRequestDTO;
import com.healtcaremanagement.patientservice.dto.PatientResponseDTO;
import com.healtcaremanagement.patientservice.enums.BloodGroup;
import com.healtcaremanagement.patientservice.model.Patient;

public class PatientMapper {

    public static PatientResponseDTO toDTO(Patient patient) {

        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();

        patientResponseDTO.setId(patient.getId().toString());
        patientResponseDTO.setAge(patient.getAge());
        patientResponseDTO.setBloodGroup(patient.getBloodGroup().toString());
        patientResponseDTO.setEmergencyContact(patient.getEmergencyContact());
        patientResponseDTO.setRegisteredAt(patient.getRegisteredAt().toString());
        patientResponseDTO.setAllergies(patient.getAllergies());

        return patientResponseDTO;
    }

    public static Patient toModel(PatientRequestDTO patientRequestDTO) {

        Patient patient = new Patient();

        patient.setAge(Integer.parseInt(patientRequestDTO.getAge()));

        if (patientRequestDTO.getAllergies() == null)
            patient.setAllergies("");
        else 
            patient.setAllergies(patientRequestDTO.getAllergies());
            
        patient.setEmergencyContact(patientRequestDTO.getEmergencyContact());
        patient.setRegisteredAt(LocalDate.now());

        BloodGroup bloodGroup = BloodGroup.valueOf(patientRequestDTO.getBloodGroup().toUpperCase());
        patient.setBloodGroup(bloodGroup);

        return patient;
    }
}
