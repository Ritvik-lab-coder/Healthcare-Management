package com.healtcaremanagement.patientservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healtcaremanagement.patientservice.dto.MedicalRecordRequestDTO;
import com.healtcaremanagement.patientservice.dto.MedicalRecordResponseDTO;
import com.healtcaremanagement.patientservice.dto.MedicalRecordUpdateRequestDTO;
import com.healtcaremanagement.patientservice.exception.InternalServerErrorException;
import com.healtcaremanagement.patientservice.exception.PatientNotFoundException;
import com.healtcaremanagement.patientservice.exception.RecordNotFoundException;
import com.healtcaremanagement.patientservice.mapper.MedicalRecordMapper;
import com.healtcaremanagement.patientservice.model.MedicalRecord;
import com.healtcaremanagement.patientservice.model.Patient;
import com.healtcaremanagement.patientservice.repository.MedicalRecordRepository;
import com.healtcaremanagement.patientservice.repository.PatientRepository;
import com.healtcaremanagement.patientservice.security.JwtUtil;
import com.healtcaremanagement.patientservice.util.ExtractIdUtil;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private JwtUtil jwtUtil;

    public MedicalRecordResponseDTO createRecord(MedicalRecordRequestDTO medicalRecordRequestDTO) {

        try {
            
            UUID id = ExtractIdUtil.extractUUID(request, jwtUtil);

            Optional<Patient> optionalPatient = patientRepository.findById(id);

            if (optionalPatient.isPresent()) {

                Patient patient = optionalPatient.get();

                MedicalRecord medicalRecord = MedicalRecordMapper.toModel(medicalRecordRequestDTO);

                medicalRecord.setPatient(patient);

                medicalRecordRepository.save(medicalRecord);

                return MedicalRecordMapper.toDTO(medicalRecord);
            } else {
                throw new PatientNotFoundException("Patient not found");
            }
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error");
        }
    }

    public MedicalRecordResponseDTO getRecordById(UUID id) {

        try {
            
            Optional<MedicalRecord> optionalMedicalRecord = medicalRecordRepository.findById(id);

            if (optionalMedicalRecord.isPresent()) {

                MedicalRecord medicalRecord = optionalMedicalRecord.get();

                return MedicalRecordMapper.toDTO(medicalRecord);
            } else {
                throw new RecordNotFoundException("Record not found");
            }
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error");
        }
    }

    public List<MedicalRecordResponseDTO> getPatientRecords() {

        try {
            
            UUID id = ExtractIdUtil.extractUUID(request, jwtUtil);

            List<MedicalRecord> medicalRecords = medicalRecordRepository.findByPatientId(id);
            List<MedicalRecordResponseDTO> medicalRecordResponseDTOs = new ArrayList<>();

            for (MedicalRecord medicalRecord : medicalRecords) {

                MedicalRecordResponseDTO medicalRecordResponseDTO = MedicalRecordMapper.toDTO(medicalRecord);
                medicalRecordResponseDTOs.add(medicalRecordResponseDTO);
            }

            return medicalRecordResponseDTOs;
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error");
        }
    }

    public MedicalRecordResponseDTO updateRecord(UUID id, MedicalRecordUpdateRequestDTO medicalRecordUpdateRequestDTO) {

        try {
            
            Optional<MedicalRecord> optionalMedicalRecord = medicalRecordRepository.findById(id);

            if (optionalMedicalRecord.isPresent()) {

                MedicalRecord medicalRecord = optionalMedicalRecord.get();

                if (medicalRecordUpdateRequestDTO.getDiagnosis() != null) {
                    System.out.println(medicalRecordUpdateRequestDTO.getDiagnosis());
                    medicalRecord.setDiagnosis(medicalRecordUpdateRequestDTO.getDiagnosis());
                }

                if (medicalRecordUpdateRequestDTO.getTreatment() != null) {
                    System.out.println(medicalRecordUpdateRequestDTO.getTreatment());
                    medicalRecord.setTreatment(medicalRecordUpdateRequestDTO.getTreatment());
                }

                if (medicalRecordUpdateRequestDTO.getDoctorName() != null) {
                    System.out.println(medicalRecordUpdateRequestDTO.getDoctorName());
                    medicalRecord.setDoctorName(medicalRecordUpdateRequestDTO.getDoctorName());
                }

                if (medicalRecordUpdateRequestDTO.getNotes() != null) {
                    System.out.println(medicalRecordUpdateRequestDTO.getNotes());
                    medicalRecord.setNotes(medicalRecordUpdateRequestDTO.getNotes());
                }

                if (medicalRecordUpdateRequestDTO.getVisitDate() != null) {
                    System.out.println(medicalRecordUpdateRequestDTO.getVisitDate());
                    medicalRecord.setVisitDate(LocalDate.parse(medicalRecordUpdateRequestDTO.getVisitDate()));
                }

                medicalRecordRepository.save(medicalRecord);

                return MedicalRecordMapper.toDTO(medicalRecord);
            } else {
                throw new RecordNotFoundException("Record not found");
            }
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error");
        }
    }

    public void deleteRecord(UUID id) {

        try {
            
            medicalRecordRepository.deleteById(id);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error");
        }
    }
}
