package com.personal.patient.account.models;

import com.personal.patient.account.entities.Doctor;
import com.personal.patient.account.entities.Services;
import com.personal.patient.account.entities.Specialization;
import com.personal.patient.account.models.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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

    private String dateOfBirth;

    private Gender gender;

    private Long hospitalId;

    private List<CreatingSpecialization> specializations;

    private List<CreatingServices> services;

    private String startTime;

    private String endTime;

    public CreatingDoctorRequest(Doctor doctor){
        this.id = doctor.getId();
        this.firstName = doctor.getFirstName();
        this.middleName = doctor.getMiddleName();
        this.lastName = doctor.getLastName();
        this.lastName = doctor.getLastName();
        this.dateOfBirth = parseDateToString(doctor.getDateOfBirth());
        this.gender = doctor.getGender();
        //this.hospitalId = doctor.getHospital().getId();
        this.services = doctor.getServices().stream().map(CreatingServices::new).collect(Collectors.toList());
        this.specializations = doctor.getSpecializations().stream().map(CreatingSpecialization::new).collect(Collectors.toList());
        this.startTime = convertTimeToString(doctor.getStartTime().getTime());
        this.endTime = convertTimeToString(doctor.getEndTime().getTime());
    }
    public String convertTimeToString(long timeInMillis) {
        Instant instant = Instant.ofEpochMilli(timeInMillis);
        LocalTime localTime = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(3)).toLocalTime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return localTime.format(formatter);
    }
    public String parseDateToString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

}
