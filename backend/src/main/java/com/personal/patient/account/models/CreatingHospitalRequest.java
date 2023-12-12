package com.personal.patient.account.models;

import com.personal.patient.account.entities.Hospital;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatingHospitalRequest {
    private Long id;

    private String name;

    private String address;

    private String openingTime;

    private String closingTime;

    public CreatingHospitalRequest(Hospital hospital){
        this.id = hospital.getId();
        this.name = hospital.getName();
        this.address = hospital.getAddress();
        this.openingTime = convertTimeToString(hospital.getOpeningTime().getTime());
        this.closingTime = convertTimeToString(hospital.getClosingTime().getTime());
    }

    public String convertTimeToString(long timeInMillis) {
        Instant instant = Instant.ofEpochMilli(timeInMillis);
        LocalTime localTime = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(3)).toLocalTime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return localTime.format(formatter);
    }
}
