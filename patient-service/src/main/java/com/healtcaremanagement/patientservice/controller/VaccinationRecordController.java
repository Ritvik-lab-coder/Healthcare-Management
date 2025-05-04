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

import com.healtcaremanagement.patientservice.dto.VaccinationRecordRequestDTO;
import com.healtcaremanagement.patientservice.dto.VaccinationRecordResponseDTO;
import com.healtcaremanagement.patientservice.dto.VaccinationRecordUpdateRequestDTO;
import com.healtcaremanagement.patientservice.service.VaccinationRecordService;

@RestController
@RequestMapping("/vaccine")
public class VaccinationRecordController {

    @Autowired
    private VaccinationRecordService vaccinationRecordService;

    @PostMapping("/create")
    public ResponseEntity<VaccinationRecordResponseDTO> createVaccinationRecord(@RequestBody VaccinationRecordRequestDTO vaccinationRecordRequestDTO) {

        VaccinationRecordResponseDTO vaccinationRecordResponseDTO = vaccinationRecordService.createVaccinationRecord(vaccinationRecordRequestDTO);

        return new ResponseEntity<>(vaccinationRecordResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<VaccinationRecordResponseDTO> getVaccineRecordById(@PathVariable("id") UUID id) {

        VaccinationRecordResponseDTO vaccinationRecordResponseDTO = vaccinationRecordService.getVaccineRecordById(id);

        return ResponseEntity.ok().body(vaccinationRecordResponseDTO);
    }

    @GetMapping("/get")
    public ResponseEntity<List<VaccinationRecordResponseDTO>> getPatientVaccineRecords() {

        List<VaccinationRecordResponseDTO> vaccinationRecordResponseDTOs = vaccinationRecordService.getPatientVaccineRecords();

        return ResponseEntity.ok().body(vaccinationRecordResponseDTOs);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<VaccinationRecordResponseDTO> updateVaccineRecord(@PathVariable("id") UUID id, @RequestBody VaccinationRecordUpdateRequestDTO vaccinationRecordUpdateRequestDTO) {

        VaccinationRecordResponseDTO vaccinationRecordResponseDTO = vaccinationRecordService.updateVaccineRecord(id, vaccinationRecordUpdateRequestDTO);

        return ResponseEntity.ok().body(vaccinationRecordResponseDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVaccineRecord(@PathVariable("id") UUID id) {

        vaccinationRecordService.deleteRecordById(id);

        return ResponseEntity.ok().body("Vaccine record deleted successfully");
    }
}
