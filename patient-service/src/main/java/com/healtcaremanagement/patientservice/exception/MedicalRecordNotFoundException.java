package com.healtcaremanagement.patientservice.exception;

public class MedicalRecordNotFoundException extends RuntimeException {

    public MedicalRecordNotFoundException(String message) {
        super(message);
    }
}
