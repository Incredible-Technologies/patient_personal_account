package com.personal.patient.account.exceptions;

public class OverAppointmentException extends RuntimeException {
    public OverAppointmentException(String message) {
        super(message);
    }
}