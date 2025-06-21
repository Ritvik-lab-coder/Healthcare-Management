package com.healtcaremanagement.patientservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.healtcaremanagement.patientservice.dto.PatientProfileResponseDTO;
import com.healtcaremanagement.patientservice.dto.PatientRequestDTO;
import com.healtcaremanagement.patientservice.dto.PatientResponseDTO;
import com.healtcaremanagement.patientservice.dto.UpdatePatientDetailsRequestDTO;
import com.healtcaremanagement.patientservice.dto.UserResponseDTO;
import com.healtcaremanagement.patientservice.enums.BloodGroup;
import com.healtcaremanagement.patientservice.exception.InternalServerErrorException;
import com.healtcaremanagement.patientservice.exception.PatientNotFoundException;
import com.healtcaremanagement.patientservice.mapper.PatientMapper;
import com.healtcaremanagement.patientservice.mapper.PatientProfileMapper;
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

    @Autowired
    @Qualifier("authWebClient")
    private WebClient authWebClient;

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

    public PatientProfileResponseDTO getProfile(UUID id) {

        try {

            String authHeader = request.getHeader("Authorization");
            
            UserResponseDTO userResponseDTO = authWebClient.get()
                .uri("/user/get/" + id.toString())
                .header("Authorization", authHeader)
                .retrieve()
                .bodyToMono(UserResponseDTO.class)
                .block();

            Optional<Patient> optionalPatient = patientRepository.findById(id);

            if (optionalPatient.isPresent()) {

                Patient patient = optionalPatient.get();
                
                PatientProfileResponseDTO patientProfileResponseDTO = PatientProfileMapper.toDTO(userResponseDTO, patient);

                return patientProfileResponseDTO;
            } else {
                throw new PatientNotFoundException("Patient not found");
            }
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public PatientProfileResponseDTO getPatientProfile() {

        try {
            
            UUID id = ExtractIdUtil.extractUUID(request, jwtUtil);

            return getProfile(id);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public PatientResponseDTO updatePatientDetails(UpdatePatientDetailsRequestDTO updatePatientDetailsRequestDTO) {

        try {
            
            UUID id = ExtractIdUtil.extractUUID(request, jwtUtil);

            Optional<Patient> optionalPatient = patientRepository.findById(id);

            if (optionalPatient.isPresent()) {

                Patient patient = optionalPatient.get();

                if (updatePatientDetailsRequestDTO.getAge() != null)
                    patient.setAge(updatePatientDetailsRequestDTO.getAge());
                
                if (updatePatientDetailsRequestDTO.getBloodGroup() != null) {

                    BloodGroup bloodGroup = BloodGroup.valueOf(updatePatientDetailsRequestDTO.getBloodGroup().toUpperCase());
                    patient.setBloodGroup(bloodGroup);
                }

                if (updatePatientDetailsRequestDTO.getEmergencyContact() != null)
                    patient.setEmergencyContact(updatePatientDetailsRequestDTO.getEmergencyContact());

                if (updatePatientDetailsRequestDTO.getAllergies() != null)
                    patient.setAllergies(updatePatientDetailsRequestDTO.getAllergies());

                patientRepository.save(patient);

                return PatientMapper.toDTO(patient);
            } else {
                throw new PatientNotFoundException("Patient not found");
            }
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public void deletePatient() {

        try {
            
            UUID id = ExtractIdUtil.extractUUID(request, jwtUtil);

            patientRepository.deleteById(id);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public void deletePatientById(UUID id) {

        try {
            
            patientRepository.deleteById(id);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public List<PatientResponseDTO> getAllPatients() {

        try {
            
            List<Patient> patients = patientRepository.findAll();

            List<PatientResponseDTO> patientResponseDTOs = new ArrayList<>();
            for (Patient patient : patients) {
                patientResponseDTOs.add(PatientMapper.toDTO(patient));
            }

            return patientResponseDTOs;
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }
}
