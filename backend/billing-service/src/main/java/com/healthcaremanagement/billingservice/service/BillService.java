package com.healthcaremanagement.billingservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.healthcaremanagement.billingservice.dto.BillRequestDTO;
import com.healthcaremanagement.billingservice.dto.BillResponseDTO;
import com.healthcaremanagement.billingservice.dto.BillUpdateRequestDTO;
import com.healthcaremanagement.billingservice.dto.UserResponseDTO;
import com.healthcaremanagement.billingservice.exception.InternalServerErrorException;
import com.healthcaremanagement.billingservice.mapper.BillMapper;
import com.healthcaremanagement.billingservice.model.Bill;
import com.healthcaremanagement.billingservice.repository.BillRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    @Qualifier("authWebClient")
    private WebClient authWebClient;

    @Autowired
    private HttpServletRequest request;

    public BillResponseDTO createBill(UUID id, BillRequestDTO billRequestDTO) {

        try {
            
            String holderId = id.toString();

            UserResponseDTO userResponseDTO = authWebClient.get() 
                                                .uri("/user/get/" + holderId)
                                                .header("Authorization", request.getHeader("Authorization"))
                                                .retrieve()
                                                .bodyToMono(UserResponseDTO.class)
                                                .block();

            System.out.println(userResponseDTO);
            
            Bill bill = BillMapper.toModel(billRequestDTO, userResponseDTO);

            billRepository.save(bill);

            return BillMapper.toDTO(bill);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error");
        }
    }

    public List<BillResponseDTO> getAllBills() {

        try {
            
            List<Bill> bills = billRepository.findAll();

            List<BillResponseDTO> billResponseDTOs = new ArrayList<>();

            for (Bill bill : bills)
                billResponseDTOs.add(BillMapper.toDTO(bill));

            return billResponseDTOs;
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error");
        }
    }

    public List<BillResponseDTO> getPatientBills(UUID id) {

        try {
            
            List<Bill> bills = billRepository.findByHolderId(id.toString());

            List<BillResponseDTO> billResponseDTOs = new ArrayList<>();

            for (Bill bill : bills)
                billResponseDTOs.add(BillMapper.toDTO(bill));

            return billResponseDTOs;
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error");
        }
    }

    public BillResponseDTO getBillById(UUID id) {

        try {
            
            Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new InternalServerErrorException("Bill not found"));

            return BillMapper.toDTO(bill);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error");
        }
    }

    public BillResponseDTO updateBill(UUID id, BillUpdateRequestDTO billUpdateRequestDTO) {
        
        try {
            
            Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new InternalServerErrorException("Bill not found"));

            if (billUpdateRequestDTO.getAmount() != null) 
                bill.setAmount(billUpdateRequestDTO.getAmount());

            if (billUpdateRequestDTO.getDescription() != null)
                bill.setDescription(billUpdateRequestDTO.getDescription());

            if (billUpdateRequestDTO.getDueDate() != null)
                bill.setDueDate(LocalDate.parse(billUpdateRequestDTO.getDueDate()));

            if (billUpdateRequestDTO.getIsPaid() != null)
                bill.setIsPaid(billUpdateRequestDTO.getIsPaid());

            billRepository.save(bill);

            return BillMapper.toDTO(bill);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error");
        }
    }
    
    public void deleteBill(UUID id) {
        
        try {
            
            billRepository.deleteById(id);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error");
        }
    }
}
