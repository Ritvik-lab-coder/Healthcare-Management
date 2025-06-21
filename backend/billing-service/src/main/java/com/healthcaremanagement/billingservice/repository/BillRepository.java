package com.healthcaremanagement.billingservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcaremanagement.billingservice.model.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, UUID> {

    public List<Bill> findByHolderId(String id);
}
