package com.healthcaremanagement.billingservice.dto;

import lombok.Data;

@Data
public class BillUpdateRequestDTO {

    private String description;

    private Float amount;

    private String dueDate;

    private Boolean isPaid;
}
