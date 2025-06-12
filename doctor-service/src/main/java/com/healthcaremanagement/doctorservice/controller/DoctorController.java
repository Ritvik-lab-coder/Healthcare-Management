package com.healthcaremanagement.doctorservice.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcaremanagement.doctorservice.dto.DoctorRequestDTO;
import com.healthcaremanagement.doctorservice.dto.DoctorResponseDTO;
import com.healthcaremanagement.doctorservice.dto.PatientResponseDTO;
import com.healthcaremanagement.doctorservice.dto.UpdateDoctorDetailsRequestDTO;
import com.healthcaremanagement.doctorservice.service.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/register")
    public ResponseEntity<DoctorResponseDTO> registerDoctor(@RequestBody DoctorRequestDTO doctorRequestDTO) {

        DoctorResponseDTO doctorResponseDTO = doctorService.register(doctorRequestDTO);

        return new ResponseEntity<>(doctorResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/get-patients")
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {

        List<PatientResponseDTO> patients = doctorService.getPatients();

        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<DoctorResponseDTO>> getDoctors() {

        List<DoctorResponseDTO> doctors = doctorService.getDoctors();

        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DoctorResponseDTO> getDoctorById(@PathVariable("id") UUID id) {

        DoctorResponseDTO doctorResponseDTO = doctorService.getDoctorById(id);

        return new ResponseEntity<>(doctorResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/get/specialization/{specialization}")
    public ResponseEntity<List<DoctorResponseDTO>> getDoctorsBySpecialization(@PathVariable("specialization") String specialization) {

        List<DoctorResponseDTO> doctors = doctorService.getDoctorsBySpecialization(specialization);

        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DoctorResponseDTO> updateDoctorDetails(@PathVariable("id") UUID id, @RequestBody UpdateDoctorDetailsRequestDTO updateDoctorDetailsRequestDTO) {

        DoctorResponseDTO updatedDoctor = doctorService.updateDoctorDetails(id, updateDoctorDetailsRequestDTO);

        return new ResponseEntity<>(updatedDoctor, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable("id") UUID id) {

        doctorService.deleteDoctor(id);

        return new ResponseEntity<>("Doctor deleted Successfully", HttpStatus.OK);
    }
}
