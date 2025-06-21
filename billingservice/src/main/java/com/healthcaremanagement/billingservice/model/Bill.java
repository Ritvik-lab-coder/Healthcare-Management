package com.healthcaremanagement.billingservice.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "bills")
@Data
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String description;
    
    @NotNull
    private LocalDate dueDate;
    
    @NotNull
    private Float amount;
    
    @NotNull
    private Boolean isPaid;
    
    @NotNull
    private String holderId;
    
    @NotNull
    private String holderName;
    
    @NotNull
    private String holderEmail;
    
    @NotNull
    private String holderContact;
}
