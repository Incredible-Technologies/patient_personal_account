package com.personal.patient.account.models;

import lombok.Data;

@Data
public class CreatingResultCardResponse {
    private String name;

    private String description;

    private String dateOfShouldReady;

    private String hospitalAddress;

    private String userEmail;
}
