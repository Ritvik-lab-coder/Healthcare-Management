package com.healtcaremanagement.patientservice.mapper;

import com.healtcaremanagement.patientservice.dto.PatientProfileResponseDTO;
import com.healtcaremanagement.patientservice.dto.UserResponseDTO;
import com.healtcaremanagement.patientservice.model.Patient;

public class PatientProfileMapper {

    public static PatientProfileResponseDTO toDTO(UserResponseDTO userResponseDTO, Patient patient) {

        PatientProfileResponseDTO patientProfileResponseDTO = new PatientProfileResponseDTO();

        patientProfileResponseDTO.setId(userResponseDTO.getId());
        patientProfileResponseDTO.setName(userResponseDTO.getName());
        patientProfileResponseDTO.setEmail(userResponseDTO.getEmail());
        patientProfileResponseDTO.setGender(userResponseDTO.getGender());
        patientProfileResponseDTO.setDateOfBirth(userResponseDTO.getDateOfBirth());
        patientProfileResponseDTO.setAddress(userResponseDTO.getAddress());
        patientProfileResponseDTO.setContact(userResponseDTO.getContact());
        patientProfileResponseDTO.setRole(userResponseDTO.getRole());
        patientProfileResponseDTO.setAge(patient.getAge());
        patientProfileResponseDTO.setEmergencyContact(patient.getEmergencyContact());
        patientProfileResponseDTO.setAllergies(patient.getAllergies());
        patientProfileResponseDTO.setBloodGroup(patient.getBloodGroup().toString());

        return patientProfileResponseDTO;
    }
}
