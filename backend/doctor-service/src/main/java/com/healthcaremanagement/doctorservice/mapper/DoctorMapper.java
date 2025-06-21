package com.healthcaremanagement.doctorservice.mapper;

import java.time.LocalDate;

import com.healthcaremanagement.doctorservice.dto.DoctorRequestDTO;
import com.healthcaremanagement.doctorservice.dto.DoctorResponseDTO;
import com.healthcaremanagement.doctorservice.model.Doctor;

public class DoctorMapper {

    public static DoctorResponseDTO toDTO(Doctor doctor) {

        DoctorResponseDTO doctorResponseDTO = new DoctorResponseDTO();

        doctorResponseDTO.setId(doctor.getId().toString());
        doctorResponseDTO.setAge(doctor.getAge());
        doctorResponseDTO.setExperience(doctor.getExperience());
        doctorResponseDTO.setAvailableDays(doctor.getAvailableDays());
        doctorResponseDTO.setSpecialization(doctor.getSpecialization());
        doctorResponseDTO.setCreatedAt(doctor.getCreatedAt().toString());

        return doctorResponseDTO;
    }

    public static Doctor toModel(DoctorRequestDTO doctorRequestDTO) {

        Doctor doctor = new Doctor();

        doctor.setAge(doctorRequestDTO.getAge());
        doctor.setExperience(doctorRequestDTO.getExperience());
        doctor.setAvailableDays(doctorRequestDTO.getAvailableDays());
        doctor.setSpecialization(doctorRequestDTO.getSpecialization());
        doctor.setCreatedAt(LocalDate.now());

        return doctor;
    }
}
