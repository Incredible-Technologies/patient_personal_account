package com.personal.patient.account.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatingPassportResponse {
    private String fullName;

    private String passportGender;

    private String dateOfBirth;

    private String placeOfBirth;

    private Integer series;

    private Integer number;

    private String dateOfIssue;

    private Integer divisionCode;

    private String issuedBy;
}
