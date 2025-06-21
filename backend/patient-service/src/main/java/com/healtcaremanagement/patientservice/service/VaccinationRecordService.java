package com.healtcaremanagement.patientservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healtcaremanagement.patientservice.dto.VaccinationRecordRequestDTO;
import com.healtcaremanagement.patientservice.dto.VaccinationRecordResponseDTO;
import com.healtcaremanagement.patientservice.dto.VaccinationRecordUpdateRequestDTO;
import com.healtcaremanagement.patientservice.exception.InternalServerErrorException;
import com.healtcaremanagement.patientservice.exception.PatientNotFoundException;
import com.healtcaremanagement.patientservice.exception.VaccineRecordNotFoundException;
import com.healtcaremanagement.patientservice.mapper.VaccinationRecordMapper;
import com.healtcaremanagement.patientservice.model.Patient;
import com.healtcaremanagement.patientservice.model.VaccinationRecord;
import com.healtcaremanagement.patientservice.repository.PatientRepository;
import com.healtcaremanagement.patientservice.repository.VaccinationRecordRepository;
import com.healtcaremanagement.patientservice.security.JwtUtil;
import com.healtcaremanagement.patientservice.util.ExtractIdUtil;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class VaccinationRecordService {

    @Autowired
    private VaccinationRecordRepository vaccinationRecordRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private JwtUtil jwtUtil;

    public VaccinationRecordResponseDTO createVaccinationRecord(VaccinationRecordRequestDTO vaccinationRecordRequestDTO) {

        try {
            
            UUID id = ExtractIdUtil.extractUUID(httpServletRequest, jwtUtil);

            Optional<Patient> optionalPatient = patientRepository.findById(id);

            if (optionalPatient.isPresent()) {

                Patient patient = optionalPatient.get();

                VaccinationRecord vaccinationRecord = VaccinationRecordMapper.toModel(vaccinationRecordRequestDTO);

                vaccinationRecord.setPatient(patient);

                vaccinationRecordRepository.save(vaccinationRecord);

                return VaccinationRecordMapper.toDTO(vaccinationRecord);
            } else {
                throw new PatientNotFoundException("Patient not found");
            }
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error");
        }
    }

    public VaccinationRecordResponseDTO getVaccineRecordById(UUID id) {

        try {
            
            Optional<VaccinationRecord> optionalVaccineRecord = vaccinationRecordRepository.findById(id);

            if (optionalVaccineRecord.isPresent()) {

                VaccinationRecord vaccinationRecord = optionalVaccineRecord.get();

                return VaccinationRecordMapper.toDTO(vaccinationRecord);
            } else {
                throw new VaccineRecordNotFoundException("Vaccine record not found");
            }
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error");
        }
    }

    public List<VaccinationRecordResponseDTO> getPatientVaccineRecords() {

        try {
            
            UUID id = ExtractIdUtil.extractUUID(httpServletRequest, jwtUtil);

            Optional<Patient> optionalPatient = patientRepository.findById(id);

            if (optionalPatient.isPresent()) {

                List<VaccinationRecord> vaccinationRecords = vaccinationRecordRepository.findByPatientId(id);
                List<VaccinationRecordResponseDTO> vaccinationRecordResponseDTOs = new ArrayList<>();

                for (VaccinationRecord vaccinationRecord : vaccinationRecords) {

                    VaccinationRecordResponseDTO vaccinationRecordResponseDTO = VaccinationRecordMapper.toDTO(vaccinationRecord);
                    vaccinationRecordResponseDTOs.add(vaccinationRecordResponseDTO);
                }

                return vaccinationRecordResponseDTOs;
            } else {
                throw new PatientNotFoundException("Patient not found");
            }
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error");
        }
    }

    public VaccinationRecordResponseDTO updateVaccineRecord(UUID id, VaccinationRecordUpdateRequestDTO vaccinationRecordUpdateRequestDTO) {
        try {
            
            Optional<VaccinationRecord> optionalVaccinationRecord = vaccinationRecordRepository.findById(id);

            if (optionalVaccinationRecord.isPresent()) {

                VaccinationRecord vaccinationRecord = optionalVaccinationRecord.get();

                if (vaccinationRecordUpdateRequestDTO.getVaccineName() != null)
                    vaccinationRecord.setVaccineName(vaccinationRecordUpdateRequestDTO.getVaccineName());

                if (vaccinationRecordUpdateRequestDTO.getAdministeredBy() != null) 
                    vaccinationRecord.setAdministeredBy(vaccinationRecordUpdateRequestDTO.getAdministeredBy());

                if (vaccinationRecordUpdateRequestDTO.getDateAdministered() != null)
                    vaccinationRecord.setDateAdministered(LocalDate.parse(vaccinationRecordUpdateRequestDTO.getDateAdministered()));

                if (vaccinationRecordUpdateRequestDTO.getNotes() != null) 
                    vaccinationRecord.setNotes(vaccinationRecordUpdateRequestDTO.getNotes());

                vaccinationRecordRepository.save(vaccinationRecord);

                return VaccinationRecordMapper.toDTO(vaccinationRecord);
            } else {
                throw new VaccineRecordNotFoundException("Vaccine record not found");
            }
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error");
        }
    }

    @Transactional
    public void deleteRecordById(UUID id) {

        try {
            
            vaccinationRecordRepository.deleteByIdDirect(id);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error");
        }
    }
}
