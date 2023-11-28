package com.personal.patient.account.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatingHospitalResponse {

    private String name;

    private String address;

    private String openingTime;

    private String closingTime;
}
