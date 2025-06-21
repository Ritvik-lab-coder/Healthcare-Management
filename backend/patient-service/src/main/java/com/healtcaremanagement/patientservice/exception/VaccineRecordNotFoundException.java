package com.healtcaremanagement.patientservice.exception;

public class VaccineRecordNotFoundException extends RuntimeException {

    public VaccineRecordNotFoundException(String message) {
        super(message);
    }
}
