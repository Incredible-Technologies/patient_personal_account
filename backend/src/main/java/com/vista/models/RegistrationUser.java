package com.vista.models;

import lombok.Data;

@Data
public class RegistrationUser {
    private String email;
    private String password;
    private String confirmPassword;
}