package com.healthcaremanagement.doctorservice.mapper;

import java.util.UUID;

import com.healthcaremanagement.doctorservice.dto.ReviewRequestDTO;
import com.healthcaremanagement.doctorservice.dto.ReviewResponseDTO;
import com.healthcaremanagement.doctorservice.model.Doctor;
import com.healthcaremanagement.doctorservice.model.Review;

public class ReviewMapper {

    public static ReviewResponseDTO toDTO(Review review, String doctorName, String patientName) {

        ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO();

        reviewResponseDTO.setDoctorName(doctorName);
        reviewResponseDTO.setPatientName(patientName);
        reviewResponseDTO.setRating(review.getRating());
        reviewResponseDTO.setComment(review.getComment());

        return reviewResponseDTO;
    }

    public static Review toModel(ReviewRequestDTO reviewRequestDTO, Doctor doctor, UUID patientId) {

        Review review = new Review();

        review.setDoctor(doctor);
        review.setPatientId(patientId);
        review.setRating(reviewRequestDTO.getRating());
        review.setComment(reviewRequestDTO.getComment());
        review.setCreatedAt(reviewRequestDTO.getCreatedAt());

        return review;
    }
}
