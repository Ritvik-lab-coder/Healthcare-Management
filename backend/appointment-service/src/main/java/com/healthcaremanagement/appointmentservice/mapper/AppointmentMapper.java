package com.healthcaremanagement.appointmentservice.mapper;

import java.util.UUID;

import com.healthcaremanagement.appointmentservice.dto.AppointmentRequestDTO;
import com.healthcaremanagement.appointmentservice.dto.AppointmentResponseDTO;
import com.healthcaremanagement.appointmentservice.model.Appointment;

public class AppointmentMapper {

    public static AppointmentResponseDTO toDTO(Appointment appointment) {

        AppointmentResponseDTO appointmentResponseDTO = new AppointmentResponseDTO();

        appointmentResponseDTO.setId(appointment.getId().toString());
        appointmentResponseDTO.setSubject(appointment.getSubject());
        appointmentResponseDTO.setDate(appointment.getDate().toString());
        appointmentResponseDTO.setTime(appointment.getTime().toString());
        appointmentResponseDTO.setDoctorId(appointment.getDoctorId().toString());
        appointmentResponseDTO.setDoctorName(appointment.getDoctorName());
        appointmentResponseDTO.setPatientId(appointment.getPatientId().toString());
        appointmentResponseDTO.setPatientName(appointment.getPatientName());

        return appointmentResponseDTO;
    }

    public static Appointment toModel(UUID doctorId, UUID patientId, AppointmentRequestDTO appointmentRequestDTO, String doctorName) {

        Appointment appointment = new Appointment();

        appointment.setDate(appointmentRequestDTO.getDate());
        appointment.setTime(appointmentRequestDTO.getTime());
        appointment.setSubject(appointmentRequestDTO.getSubject());
        appointment.setDoctorId(doctorId.toString());
        appointment.setDoctorName(doctorName);
        appointment.setPatientId(patientId.toString());
        appointment.setPatientName(appointmentRequestDTO.getPatientName());

        return appointment;
    }
}
