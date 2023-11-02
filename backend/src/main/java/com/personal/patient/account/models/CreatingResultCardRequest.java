package com.personal.patient.account.models;

import lombok.Data;

@Data
public class CreatingResultCardRequest {
    private Long id;
    private String description;

    private String dateOfShouldReady;

    private String hospitalAddress;

    private String userEmail;

    public CreatingResultCardRequest(CreatingResultCardResponse response, Long id){
        this.id = id;
        this.description = response.getDescription();
        this.dateOfShouldReady = response.getDateOfShouldReady();
        this.hospitalAddress = response.getHospitalAddress();
        this.userEmail = response.getUserEmail();
    }
}
