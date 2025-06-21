package com.healthcaremanagement.billingservice.controller;

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

import com.healthcaremanagement.billingservice.dto.BillRequestDTO;
import com.healthcaremanagement.billingservice.dto.BillResponseDTO;
import com.healthcaremanagement.billingservice.dto.BillUpdateRequestDTO;
import com.healthcaremanagement.billingservice.service.BillService;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping("/create/{id}")
    public ResponseEntity<BillResponseDTO> createBill(@PathVariable("id") UUID id, @RequestBody BillRequestDTO billRequestDTO) {

        BillResponseDTO billResponseDTO = billService.createBill(id, billRequestDTO);

        return new ResponseEntity<>(billResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<BillResponseDTO>> getAllBills() {

        List<BillResponseDTO> billResponseDTOs = billService.getAllBills();

        return new ResponseEntity<>(billResponseDTOs, HttpStatus.OK);
    }

    @GetMapping("/get/patient/{id}")
    public ResponseEntity<List<BillResponseDTO>> getPatientBills(@PathVariable("id") UUID id) {

        List<BillResponseDTO> billResponseDTOs = billService.getPatientBills(id);

        return new ResponseEntity<>(billResponseDTOs, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BillResponseDTO> getBillById(@PathVariable("id") UUID id) {

        BillResponseDTO billResponseDTO = billService.getBillById(id);

        return new ResponseEntity<>(billResponseDTO, HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<BillResponseDTO> updateBill(@PathVariable("id") UUID id, @RequestBody BillUpdateRequestDTO billUpdateRequestDTO) {
        
        BillResponseDTO billResponseDTO = billService.updateBill(id, billUpdateRequestDTO);
        
        return new ResponseEntity<>(billResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBill(@PathVariable("id") UUID id) {

        billService.deleteBill(id);

        return new ResponseEntity<>("Bill deleted successfully", HttpStatus.OK);
    }
}
