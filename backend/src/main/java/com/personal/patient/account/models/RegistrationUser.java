package com.personal.patient.account.models;

import lombok.Data;

@Data
public class RegistrationUser {
    private String email;
    private String password;
    private String confirmPassword;
}