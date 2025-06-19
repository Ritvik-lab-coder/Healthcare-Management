package com.healthcaremanagement.appointmentservice.controller;

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

import com.healthcaremanagement.appointmentservice.dto.AppointmentRequestDTO;
import com.healthcaremanagement.appointmentservice.dto.AppointmentResponseDTO;
import com.healthcaremanagement.appointmentservice.dto.AppointmentUpdateRequestDTO;
import com.healthcaremanagement.appointmentservice.service.AppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/create/{id}")
    public ResponseEntity<AppointmentResponseDTO> createAppointment(@PathVariable("id") UUID doctorId, @RequestBody AppointmentRequestDTO appointmentRequestDTO) {

        AppointmentResponseDTO appointmentResponseDTO = appointmentService.createAppointment(doctorId, appointmentRequestDTO);

        return new ResponseEntity<>(appointmentResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointments() {

        List<AppointmentResponseDTO> appointmentResponseDTOs = appointmentService.getAllAppointments();

        return new ResponseEntity<>(appointmentResponseDTOs, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AppointmentResponseDTO> getAppointmentById(@PathVariable("id") UUID id) {

        AppointmentResponseDTO appointmentResponseDTO = appointmentService.getAppointmentById(id);

        return new ResponseEntity<>(appointmentResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/get/doctor/{id}")
    public ResponseEntity<List<AppointmentResponseDTO>> getDoctorAppointments(@PathVariable("id") UUID id) {

        List<AppointmentResponseDTO> appointmentResponseDTOs = appointmentService.getDoctorAppointments(id);

        return new ResponseEntity<>(appointmentResponseDTOs, HttpStatus.OK);
    }

    @GetMapping("/get/patient/{id}")
    public ResponseEntity<List<AppointmentResponseDTO>> getPatientAppointments(@PathVariable("id") UUID id) {

        List<AppointmentResponseDTO> appointmentResponseDTOs = appointmentService.getPatientAppointments(id);

        return new ResponseEntity<>(appointmentResponseDTOs, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(@PathVariable("id") UUID id, @RequestBody AppointmentUpdateRequestDTO appointmentUpdateRequestDTO) {

        AppointmentResponseDTO appointmentResponseDTO = appointmentService.updateAppointment(id, appointmentUpdateRequestDTO);

         return new ResponseEntity<>(appointmentResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable("id") UUID id) {

        String message = appointmentService.deleteAppointment(id);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
