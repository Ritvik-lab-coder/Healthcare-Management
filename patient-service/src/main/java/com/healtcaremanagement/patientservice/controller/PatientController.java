package com.healtcaremanagement.patientservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healtcaremanagement.patientservice.dto.PatientRequestDTO;
import com.healtcaremanagement.patientservice.dto.PatientResponseDTO;
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
}
