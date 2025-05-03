package com.healtcaremanagement.patientservice.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healtcaremanagement.patientservice.dto.PatientRequestDTO;
import com.healtcaremanagement.patientservice.dto.PatientResponseDTO;
import com.healtcaremanagement.patientservice.exception.InternalServerErrorException;
import com.healtcaremanagement.patientservice.mapper.PatientMapper;
import com.healtcaremanagement.patientservice.model.Patient;
import com.healtcaremanagement.patientservice.repository.PatientRepository;
import com.healtcaremanagement.patientservice.security.JwtUtil;
import com.healtcaremanagement.patientservice.util.ExtractIdUtil;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private HttpServletRequest request;

    public PatientResponseDTO registerPatientDetails(PatientRequestDTO patientRequestDTO) {

        try {

            UUID id = ExtractIdUtil.extractUUID(request, jwtUtil);

            Patient patient = PatientMapper.toModel(patientRequestDTO);

            patient.setId(id);

            patientRepository.save(patient);

            return PatientMapper.toDTO(patient);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }
}
