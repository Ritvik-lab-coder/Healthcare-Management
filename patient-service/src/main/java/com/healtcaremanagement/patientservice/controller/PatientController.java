package com.healtcaremanagement.patientservice.controller;

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

import com.healtcaremanagement.patientservice.dto.PatientProfileResponseDTO;
import com.healtcaremanagement.patientservice.dto.PatientRequestDTO;
import com.healtcaremanagement.patientservice.dto.PatientResponseDTO;
import com.healtcaremanagement.patientservice.dto.UpdatePatientDetailsRequestDTO;
import com.healtcaremanagement.patientservice.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/register")
    public ResponseEntity<PatientResponseDTO> registerPatientDetails(@RequestBody PatientRequestDTO patientRequestDTO) {

        PatientResponseDTO patientResponseDTO = patientService.registerPatientDetails(patientRequestDTO);

        return new ResponseEntity<>(patientResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<PatientProfileResponseDTO> getPatientProfileById(@PathVariable("id") UUID id) {

        PatientProfileResponseDTO patientProfileResponseDTO = patientService.getProfile(id);

        return new ResponseEntity<>(patientProfileResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<PatientProfileResponseDTO> getPatientProfile() {

        PatientProfileResponseDTO patientProfileResponseDTO = patientService.getPatientProfile();

        return new ResponseEntity<>(patientProfileResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<PatientResponseDTO> updatePatientDetails(@RequestBody UpdatePatientDetailsRequestDTO updatePatientDetailsRequestDTO) {

        PatientResponseDTO patientResponseDTO = patientService.updatePatientDetails(updatePatientDetailsRequestDTO);

        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePatient() {

        patientService.deletePatient();

        return ResponseEntity.ok().body("Patient deleted successfully");
    }
}
