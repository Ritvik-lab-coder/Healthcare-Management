package com.healthcaremanagement.doctorservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcaremanagement.doctorservice.model.Review;

public interface ReviewRepository extends JpaRepository<Review, UUID> {

    public List<Review> findByDoctorId(UUID doctorId);
}
