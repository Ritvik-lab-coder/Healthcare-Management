package com.healthcaremanagement.appointmentservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.healthcaremanagement.appointmentservice.dto.AppointmentRequestDTO;
import com.healthcaremanagement.appointmentservice.dto.AppointmentResponseDTO;
import com.healthcaremanagement.appointmentservice.dto.AppointmentUpdateRequestDTO;
import com.healthcaremanagement.appointmentservice.dto.DoctorProfileResponseDTO;
import com.healthcaremanagement.appointmentservice.exception.InternalServerErrorException;
import com.healthcaremanagement.appointmentservice.mapper.AppointmentMapper;
import com.healthcaremanagement.appointmentservice.model.Appointment;
import com.healthcaremanagement.appointmentservice.repository.AppointmentRepository;
import com.healthcaremanagement.appointmentservice.security.JwtUtil;
import com.healthcaremanagement.appointmentservice.utils.ExtractIdUtil;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    @Qualifier("doctorWebClient")
    private WebClient doctorWebClient;

    public AppointmentResponseDTO createAppointment(UUID doctorId, AppointmentRequestDTO appointmentRequestDTO) {

        try {

            UUID patientId = ExtractIdUtil.extractUUID(request, jwtUtil);

            System.out.println("above doctor web client");

            String doctorName = doctorWebClient.get()
                    .uri("/doctor/get/" + doctorId.toString())
                    .header("Authorization", request.getHeader("Authorization"))
                    .retrieve()
                    .bodyToMono(DoctorProfileResponseDTO.class)
                    .block()
                    .getName();

            System.out.println("below doctor web client");

            Appointment appointment = AppointmentMapper.toModel(doctorId, patientId, appointmentRequestDTO, doctorName);

            appointmentRepository.save(appointment);

            return AppointmentMapper.toDTO(appointment);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public List<AppointmentResponseDTO> getAllAppointments() {

        try {
            
            List<Appointment> appointments = appointmentRepository.findAll();
            List<AppointmentResponseDTO> appointmentResponseDTOs = new ArrayList<>();

            for (Appointment appointment : appointments)
                appointmentResponseDTOs.add(AppointmentMapper.toDTO(appointment));

            return appointmentResponseDTOs;
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public AppointmentResponseDTO getAppointmentById(UUID id) {

        try {
            
            Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new InternalServerErrorException("Appointment not found"));

            return AppointmentMapper.toDTO(appointment);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public List<AppointmentResponseDTO> getDoctorAppointments(UUID id) {

        try {
            
            String doctorId = id.toString();

            List<Appointment> appointments = appointmentRepository.findByDoctorId(doctorId);
            List<AppointmentResponseDTO> appointmentResponseDTOs = new ArrayList<>();

            for (Appointment appointment : appointments)
                appointmentResponseDTOs.add(AppointmentMapper.toDTO(appointment));

            return appointmentResponseDTOs;
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public List<AppointmentResponseDTO> getPatientAppointments(UUID id) {

        try {
            
            String patientId = id.toString();

            List<Appointment> appointments = appointmentRepository.findByPatientId(patientId);
            List<AppointmentResponseDTO> appointmentResponseDTOs = new ArrayList<>();

            for (Appointment appointment : appointments)
                appointmentResponseDTOs.add(AppointmentMapper.toDTO(appointment));

            return appointmentResponseDTOs;
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public AppointmentResponseDTO updateAppointment(UUID id, AppointmentUpdateRequestDTO appointmentUpdateRequestDTO) {

        try {
            
            Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new InternalServerErrorException("Appointment not found"));

            if (appointmentUpdateRequestDTO.getDate() != null)
                appointment.setDate(appointmentUpdateRequestDTO.getDate());
            
            if (appointmentUpdateRequestDTO.getPatientName() != null)
                appointment.setPatientName(appointmentUpdateRequestDTO.getPatientName());

            if (appointmentUpdateRequestDTO.getSubject() != null)
                appointment.setSubject(appointmentUpdateRequestDTO.getSubject());

            if (appointmentUpdateRequestDTO.getTime() != null)
                appointment.setTime(appointmentUpdateRequestDTO.getTime());

            appointmentRepository.save(appointment);

            return AppointmentMapper.toDTO(appointment);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public String deleteAppointment(UUID id) {

        try {
            
            appointmentRepository.deleteById(id);

            return "Appointment deleted successfully";
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }        
    }
}
