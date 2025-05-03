package com.healtcaremanagement.patientservice.controller;

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

import com.healtcaremanagement.patientservice.dto.MedicalRecordRequestDTO;
import com.healtcaremanagement.patientservice.dto.MedicalRecordResponseDTO;
import com.healtcaremanagement.patientservice.dto.MedicalRecordUpdateRequestDTO;
import com.healtcaremanagement.patientservice.service.MedicalRecordService;

@RestController
@RequestMapping("/record")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @PostMapping("/create")
    public ResponseEntity<MedicalRecordResponseDTO> createRecord(@RequestBody MedicalRecordRequestDTO medicalRecordRequestDTO) {

        MedicalRecordResponseDTO medicalRecordResponseDTO = medicalRecordService.createRecord(medicalRecordRequestDTO);

        return new ResponseEntity<>(medicalRecordResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<MedicalRecordResponseDTO> getRecordById(@PathVariable("id") UUID id) {

        MedicalRecordResponseDTO medicalRecordResponseDTO = medicalRecordService.getRecordById(id);

        return ResponseEntity.ok().body(medicalRecordResponseDTO);
    }

    @GetMapping("/get")
    public ResponseEntity<List<MedicalRecordResponseDTO>> getPatientRecords() {

        List<MedicalRecordResponseDTO> medicalRecordResponseDTOs = medicalRecordService.getPatientRecords();

        return ResponseEntity.ok().body(medicalRecordResponseDTOs);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MedicalRecordResponseDTO> updateRecord(@PathVariable("id") UUID id, @RequestBody MedicalRecordUpdateRequestDTO medicalRecordUpdateRequestDTO) {

        MedicalRecordResponseDTO medicalRecordResponseDTO = medicalRecordService.updateRecord(id, medicalRecordUpdateRequestDTO);

        return ResponseEntity.ok().body(medicalRecordResponseDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable("id") UUID id) {

        medicalRecordService.deleteRecord(id);

        return ResponseEntity.ok().body("Record deleted successfully");
    }
}
