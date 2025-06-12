package com.healthcaremanagement.doctorservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.healthcaremanagement.doctorservice.dto.DoctorRequestDTO;
import com.healthcaremanagement.doctorservice.dto.DoctorResponseDTO;
import com.healthcaremanagement.doctorservice.dto.PatientResponseDTO;
import com.healthcaremanagement.doctorservice.dto.UpdateDoctorDetailsRequestDTO;
import com.healthcaremanagement.doctorservice.exception.InternalServerErrorException;
import com.healthcaremanagement.doctorservice.mapper.DoctorMapper;
import com.healthcaremanagement.doctorservice.model.Doctor;
import com.healthcaremanagement.doctorservice.repository.DoctorRepository;
import com.healthcaremanagement.doctorservice.security.JwtUtil;
import com.healthcaremanagement.doctorservice.util.ExtractIdUtil;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    @Qualifier("patientWebClient")
    private WebClient patientWebClient;

    public DoctorResponseDTO register(DoctorRequestDTO doctorRequestDTO) {

        try {
            
            UUID id = ExtractIdUtil.extractUUID(request, jwtUtil);

            Doctor doctor = DoctorMapper.toModel(doctorRequestDTO);

            doctor.setId(id);

            doctorRepository.save(doctor);

            return DoctorMapper.toDTO(doctor);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public List<PatientResponseDTO> getPatients() {

        try {
            
            String authHeader = request.getHeader("Authorization");

            List<PatientResponseDTO> patientResponseDTOs = patientWebClient.get()
                    .uri("/patient/get")
                    .header("Authorization", authHeader)
                    .retrieve()
                    .bodyToFlux(PatientResponseDTO.class)
                    .collectList()
                    .block();

            return patientResponseDTOs;
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public List<DoctorResponseDTO> getDoctors() {

        try {
            List<Doctor> doctors = doctorRepository.findAll();

            List<DoctorResponseDTO> doctorResponseDTOs = new ArrayList<>();
            for (Doctor doctor : doctors)
                doctorResponseDTOs.add(DoctorMapper.toDTO(doctor));
                
            return doctorResponseDTOs;
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public DoctorResponseDTO getDoctorById(UUID id) {

        try {
            Doctor doctor = doctorRepository.findById(id)
                    .orElseThrow(() -> new InternalServerErrorException("Doctor not found"));

            return DoctorMapper.toDTO(doctor);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public List<DoctorResponseDTO> getDoctorsBySpecialization(String specialization) {

        try {
            List<Doctor> doctors = doctorRepository.findBySpecialization(specialization);

            List<DoctorResponseDTO> doctorResponseDTOs = new ArrayList<>();
            for (Doctor doctor : doctors)
                doctorResponseDTOs.add(DoctorMapper.toDTO(doctor));
                
            return doctorResponseDTOs;
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public DoctorResponseDTO updateDoctorDetails(UUID id, UpdateDoctorDetailsRequestDTO updateDoctorDetailsRequestDTO) {

        try {
            Doctor doctor = doctorRepository.findById(id)
                    .orElseThrow(() -> new InternalServerErrorException("Doctor not found"));

            if (updateDoctorDetailsRequestDTO.getAge() != null)
                doctor.setAge(updateDoctorDetailsRequestDTO.getAge());

            if (updateDoctorDetailsRequestDTO.getExperience() != null)
                doctor.setExperience(updateDoctorDetailsRequestDTO.getExperience());

            if (updateDoctorDetailsRequestDTO.getAvailableDays() != null)
                doctor.setAvailableDays(updateDoctorDetailsRequestDTO.getAvailableDays());

            if (updateDoctorDetailsRequestDTO.getSpecialization() != null)
                doctor.setSpecialization(updateDoctorDetailsRequestDTO.getSpecialization());

            doctorRepository.save(doctor);

            return DoctorMapper.toDTO(doctor);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public void deleteDoctor(UUID id) {

        try {
            
            doctorRepository.deleteById(id);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }
}
