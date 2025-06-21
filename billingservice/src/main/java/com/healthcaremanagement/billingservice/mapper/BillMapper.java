package com.healthcaremanagement.billingservice.mapper;

import com.healthcaremanagement.billingservice.dto.BillRequestDTO;
import com.healthcaremanagement.billingservice.dto.BillResponseDTO;
import com.healthcaremanagement.billingservice.dto.UserResponseDTO;
import com.healthcaremanagement.billingservice.model.Bill;

public class BillMapper {

    public static BillResponseDTO toDTO(Bill bill) {

        BillResponseDTO billResponseDTO = new BillResponseDTO();

        billResponseDTO.setId(bill.getId().toString());
        billResponseDTO.setDescription(bill.getDescription());
        billResponseDTO.setAmount(bill.getAmount());
        billResponseDTO.setDueDate(bill.getDueDate().toString());
        billResponseDTO.setHolderId(bill.getHolderId());
        billResponseDTO.setHolderName(bill.getHolderName());
        billResponseDTO.setHolderContact(bill.getHolderContact());
        billResponseDTO.setHolderEmail(bill.getHolderEmail());
        billResponseDTO.setPaid(bill.getIsPaid());

        return billResponseDTO;
    }

    public static Bill toModel(BillRequestDTO billRequestDTO, UserResponseDTO userResponseDTO) {

        Bill bill = new Bill();

        bill.setDescription(billRequestDTO.getDescription());
        bill.setAmount(billRequestDTO.getAmount());
        bill.setDueDate(billRequestDTO.getDueDate());
        bill.setIsPaid(false);
        bill.setHolderId(userResponseDTO.getId());
        bill.setHolderName(userResponseDTO.getName());
        bill.setHolderContact(userResponseDTO.getContact());
        bill.setHolderEmail(userResponseDTO.getEmail());

        return bill;
    }
}
