package com.healthcaremanagement.appointmentservice.dto;

import lombok.Data;

@Data
public class BillResponseDTO {

    private String id;

    private String description;

    private float amount;

    private String dueDate;

    private String holderId;

    private boolean isPaid;

    private String holderName;

    private String holderEmail;

    private String holderContact;
}
