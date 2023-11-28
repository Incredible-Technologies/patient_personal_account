package com.personal.patient.account.models;

import com.personal.patient.account.entities.Doctor;
import com.personal.patient.account.entities.Services;
import com.personal.patient.account.entities.Specialization;
import com.personal.patient.account.models.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class CreatingDoctorRequest {
    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;

    private Date dateOfBirth;

    private Gender gender;

    private Long hospitalId;

    private List<CreatingSpecialization> specializations;

    private List<CreatingServices> services;

    public CreatingDoctorRequest(Doctor doctor){
        this.id = doctor.getId();
        this.firstName = doctor.getFirstName();
        this.middleName = doctor.getMiddleName();
        this.lastName = doctor.getLastName();
        this.lastName = doctor.getLastName();
        this.dateOfBirth = doctor.getDateOfBirth();
        this.gender = doctor.getGender();
        this.hospitalId = doctor.getHospital().getId();
        this.services = doctor.getServices().stream().map(CreatingServices::new).collect(Collectors.toList());
        this.specializations = doctor.getSpecializations().stream().map(CreatingSpecialization::new).collect(Collectors.toList());
    }
}
