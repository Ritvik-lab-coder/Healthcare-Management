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

import com.healthcaremanagement.doctorservice.dto.DoctorProfileResponseDTO;
import com.healthcaremanagement.doctorservice.dto.DoctorRequestDTO;
import com.healthcaremanagement.doctorservice.dto.DoctorResponseDTO;
import com.healthcaremanagement.doctorservice.dto.PatientResponseDTO;
import com.healthcaremanagement.doctorservice.dto.ReviewRequestDTO;
import com.healthcaremanagement.doctorservice.dto.ReviewResponseDTO;
import com.healthcaremanagement.doctorservice.dto.ScheduleRequestDTO;
import com.healthcaremanagement.doctorservice.dto.ScheduleResponseDTO;
import com.healthcaremanagement.doctorservice.dto.ScheduleUpdateRequestDTO;
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
    public ResponseEntity<DoctorProfileResponseDTO> getDoctorById(@PathVariable("id") UUID id) {

        DoctorProfileResponseDTO doctorProfileResponseDTO = doctorService.getDoctorById(id);

        return new ResponseEntity<>(doctorProfileResponseDTO, HttpStatus.OK);
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

    @PostMapping("/review/{doctorId}")
    public ResponseEntity<String> addReview(@PathVariable("doctorId") UUID doctorId, @RequestBody ReviewRequestDTO reviewRequestDTO) {

        doctorService.addReview(doctorId, reviewRequestDTO);

        return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/get/reviews/{doctorId}")
    public ResponseEntity<List<ReviewResponseDTO>> getReviews(@PathVariable("doctorId") UUID doctorId) {

        List<ReviewResponseDTO> reviews = doctorService.getReviewsByDoctorId(doctorId);

        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping("/add/schedule/{doctorId}")
    public ResponseEntity<String> addSchedule(@PathVariable("doctorId") UUID doctorId, @RequestBody ScheduleRequestDTO scheduleRequestDTO) {

        doctorService.addSchedule(doctorId, scheduleRequestDTO);

        return new ResponseEntity<>("Schedule added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/get/schedule/{doctorId}")
    public ResponseEntity<List<ScheduleResponseDTO>> getDoctorSchedule(@PathVariable("doctorId") UUID doctorId) {

        List<ScheduleResponseDTO> schedule = doctorService.getDoctorSchedule(doctorId);

        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @PutMapping("/update/schedule/{scheduleId}")
    public ResponseEntity<String> updateSchedule(@PathVariable("scheduleId") UUID scheduleId, @RequestBody ScheduleUpdateRequestDTO secheduleUpdateRequestDTO) {

        doctorService.updateSchedule(scheduleId, secheduleUpdateRequestDTO);

        return new ResponseEntity<>("Schedule updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/schedule/{scheduleId}")
    public ResponseEntity<String> deleteSchedule(@PathVariable("scheduleId") UUID scheduleId) {

        doctorService.deleteSchedule(scheduleId);

        return new ResponseEntity<>("Schedule deleted successfully", HttpStatus.OK);
    }
}
