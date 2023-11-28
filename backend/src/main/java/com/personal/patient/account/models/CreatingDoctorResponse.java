package com.personal.patient.account.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatingDoctorResponse {
    private String firstName;

    private String middleName;

    private String lastName;

    private String dateOfBirth;

    private String gender;

    private Long hospitalId;

    private List<CreatingSpecialization> specializations;

    private List<CreatingServices> services;
}
