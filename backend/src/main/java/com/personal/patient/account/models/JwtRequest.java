package com.personal.patient.account.models;

import lombok.Data;

@Data
public class JwtRequest {
    private String email;
    private String password;
}
