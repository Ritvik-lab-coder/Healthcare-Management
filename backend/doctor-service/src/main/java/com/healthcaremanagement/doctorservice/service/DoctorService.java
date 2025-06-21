package com.healthcaremanagement.doctorservice.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

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
import com.healthcaremanagement.doctorservice.dto.UserResponseDTO;
import com.healthcaremanagement.doctorservice.exception.InternalServerErrorException;
import com.healthcaremanagement.doctorservice.mapper.DoctorMapper;
import com.healthcaremanagement.doctorservice.mapper.ReviewMapper;
import com.healthcaremanagement.doctorservice.mapper.ScheduleMapper;
import com.healthcaremanagement.doctorservice.model.Doctor;
import com.healthcaremanagement.doctorservice.model.Review;
import com.healthcaremanagement.doctorservice.model.Schedule;
import com.healthcaremanagement.doctorservice.repository.DoctorRepository;
import com.healthcaremanagement.doctorservice.repository.ReviewRepository;
import com.healthcaremanagement.doctorservice.repository.ScheduleRepository;
import com.healthcaremanagement.doctorservice.security.JwtUtil;
import com.healthcaremanagement.doctorservice.util.ExtractIdUtil;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    @Qualifier("patientWebClient")
    private WebClient patientWebClient;

    @Autowired
    @Qualifier("authWebClient")
    private WebClient authWebClient;

    public DoctorResponseDTO register(DoctorRequestDTO doctorRequestDTO) {

        try {

            UUID id = ExtractIdUtil.extractUUID(request, jwtUtil);

            Doctor doctor = DoctorMapper.toModel(doctorRequestDTO);

            doctor.setId(id);

            doctorRepository.save(doctor);

            return DoctorMapper.toDTO(doctor);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public List<PatientResponseDTO> getPatients() {

        try {

            String authHeader = request.getHeader("Authorization");

            List<PatientResponseDTO> patientResponseDTOs = patientWebClient.get()
                    .uri("/patient/get")
                    .header("Authorization", authHeader)
                    .retrieve()
                    .bodyToFlux(PatientResponseDTO.class)
                    .collectList()
                    .block();

            return patientResponseDTOs;
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public List<DoctorResponseDTO> getDoctors() {

        try {
            List<Doctor> doctors = doctorRepository.findAll();

            List<DoctorResponseDTO> doctorResponseDTOs = new ArrayList<>();
            for (Doctor doctor : doctors)
                doctorResponseDTOs.add(DoctorMapper.toDTO(doctor));

            return doctorResponseDTOs;
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public DoctorProfileResponseDTO getDoctorById(UUID id) {

        try {
            Doctor doctor = doctorRepository.findById(id)
                    .orElseThrow(() -> new InternalServerErrorException("Doctor not found"));

            UserResponseDTO userResponseDTO = authWebClient.get()
                    .uri("/user/get/" + id.toString())
                    .header("Authorization", request.getHeader("Authorization"))
                    .retrieve()
                    .bodyToMono(UserResponseDTO.class)
                    .block();

            DoctorProfileResponseDTO doctorProfileResponseDTO = new DoctorProfileResponseDTO();

            doctorProfileResponseDTO.setId(doctor.getId().toString());
            doctorProfileResponseDTO.setAge(doctor.getAge());
            doctorProfileResponseDTO.setAddress(userResponseDTO.getAddress());
            doctorProfileResponseDTO.setAvailableDays(doctor.getAvailableDays());
            doctorProfileResponseDTO.setContact(userResponseDTO.getContact());
            doctorProfileResponseDTO.setCreatedAt(doctor.getCreatedAt().toString());
            doctorProfileResponseDTO.setDateOfBirth(userResponseDTO.getDateOfBirth());
            doctorProfileResponseDTO.setEmail(userResponseDTO.getEmail());
            doctorProfileResponseDTO.setExperience(doctor.getExperience());
            doctorProfileResponseDTO.setGender(userResponseDTO.getGender().toString());
            doctorProfileResponseDTO.setName(userResponseDTO.getName());
            doctorProfileResponseDTO.setSpecialization(doctor.getSpecialization());

            return doctorProfileResponseDTO;
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public List<DoctorResponseDTO> getDoctorsBySpecialization(String specialization) {

        try {
            List<Doctor> doctors = doctorRepository.findBySpecialization(specialization);

            List<DoctorResponseDTO> doctorResponseDTOs = new ArrayList<>();
            for (Doctor doctor : doctors)
                doctorResponseDTOs.add(DoctorMapper.toDTO(doctor));

            return doctorResponseDTOs;
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public DoctorResponseDTO updateDoctorDetails(UUID id, UpdateDoctorDetailsRequestDTO updateDoctorDetailsRequestDTO) {

        try {
            Doctor doctor = doctorRepository.findById(id)
                    .orElseThrow(() -> new InternalServerErrorException("Doctor not found"));

            if (updateDoctorDetailsRequestDTO.getAge() != null)
                doctor.setAge(updateDoctorDetailsRequestDTO.getAge());

            if (updateDoctorDetailsRequestDTO.getExperience() != null)
                doctor.setExperience(updateDoctorDetailsRequestDTO.getExperience());

            if (updateDoctorDetailsRequestDTO.getAvailableDays() != null)
                doctor.setAvailableDays(updateDoctorDetailsRequestDTO.getAvailableDays());

            if (updateDoctorDetailsRequestDTO.getSpecialization() != null)
                doctor.setSpecialization(updateDoctorDetailsRequestDTO.getSpecialization());

            doctorRepository.save(doctor);

            return DoctorMapper.toDTO(doctor);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public void deleteDoctor(UUID id) {

        try {

            doctorRepository.deleteById(id);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public void addReview(UUID doctorId, ReviewRequestDTO reviewRequestDTO) {

        try {
            Doctor doctor = doctorRepository.findById(doctorId)
                    .orElseThrow(() -> new InternalServerErrorException("Doctor not found"));

            UUID patientId = ExtractIdUtil.extractUUID(request, jwtUtil);

            Review review = ReviewMapper.toModel(reviewRequestDTO, doctor, patientId);

            reviewRepository.save(review);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public List<ReviewResponseDTO> getReviewsByDoctorId(UUID doctorId) {

        try {
            List<Review> reviews = reviewRepository.findByDoctorId(doctorId);

            List<ReviewResponseDTO> reviewResponseDTOs = new ArrayList<>();

            String doctorName = authWebClient.get()
                    .uri("/user/get/" + doctorId.toString())
                    .header("Authorization", request.getHeader("Authorization"))
                    .retrieve()
                    .bodyToMono(UserResponseDTO.class)
                    .block()
                    .getName();

            for (Review review : reviews) {

                String patientName = authWebClient.get()
                        .uri("/user/get/" + review.getPatientId().toString())
                        .header("Authorization", request.getHeader("Authorization"))
                        .retrieve()
                        .bodyToMono(UserResponseDTO.class)
                        .block()
                        .getName();

                reviewResponseDTOs.add(ReviewMapper.toDTO(review, doctorName, patientName));
            }

            return reviewResponseDTOs;
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public void addSchedule(UUID doctorId, ScheduleRequestDTO scheduleRequestDTO) {

        try {
            Doctor doctor = doctorRepository.findById(doctorId)
                    .orElseThrow(() -> new InternalServerErrorException("Doctor not found"));

            Schedule schedule = ScheduleMapper.toModel(scheduleRequestDTO, doctor);

            scheduleRepository.save(schedule);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public List<ScheduleResponseDTO> getDoctorSchedule(UUID doctorId) {

        try {

            List<ScheduleResponseDTO> scheduleResponseDTOs = new ArrayList<>();

            List<Schedule> schedules = scheduleRepository.findByDoctorId(doctorId);

            String doctorName = authWebClient.get()
                    .uri("/user/get/" + doctorId.toString())
                    .header("Authorization", request.getHeader("Authorization"))
                    .retrieve()
                    .bodyToMono(UserResponseDTO.class)
                    .block()
                    .getName();

            for (Schedule schedule : schedules)
                scheduleResponseDTOs.add(ScheduleMapper.toDTO(schedule, doctorName));

            return scheduleResponseDTOs;
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    public void updateSchedule(UUID scheduleId, ScheduleUpdateRequestDTO scheduleUpdateRequestDTO) {

        try {

            Schedule schedule = scheduleRepository.findById(scheduleId)
                    .orElseThrow(() -> new InternalServerErrorException("Schedule not found"));

            if (scheduleUpdateRequestDTO.getDayOfWeek() != null)
                schedule.setDayOfWeek(scheduleUpdateRequestDTO.getDayOfWeek());

            if (scheduleUpdateRequestDTO.getStartTime() != null)
                schedule.setStartTime(LocalTime.parse(scheduleUpdateRequestDTO.getStartTime()));

            if (scheduleUpdateRequestDTO.getEndTime() != null)
                schedule.setEndTime(LocalTime.parse(scheduleUpdateRequestDTO.getEndTime()));

            if (scheduleUpdateRequestDTO.getIsAvailable() != null) {
                if (scheduleUpdateRequestDTO.getIsAvailable().equals("true")) {
                    schedule.setAvailable(true);
                } else if (scheduleUpdateRequestDTO.getIsAvailable().equals("false")) {
                    schedule.setAvailable(false);
                } else {
                    throw new InternalServerErrorException("Invalid availability status");
                }
            }

            scheduleRepository.save(schedule);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

    @Transactional
    public void deleteSchedule(UUID scheduleId) {

        try {
            
            Schedule schedule = scheduleRepository.findById(scheduleId)
                    .orElseThrow(() -> new InternalServerErrorException("Schedule not found"));
    
            Doctor doctor = schedule.getDoctor();
            doctor.getSchedules().remove(schedule);
    
            schedule.setDoctor(null);
    
            doctorRepository.save(doctor);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }
    }

}
