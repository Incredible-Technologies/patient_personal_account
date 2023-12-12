package com.personal.patient.account.models;

import com.personal.patient.account.entities.Appointment;
import com.personal.patient.account.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class CreatingAppointmentRequest {
    private Long id;

    private Long doctorId;
    private Long serviceId;

    private Long hospitalId;
    private String date;

    private String startTime;

    private String endTime;


    public CreatingAppointmentRequest(Appointment appointment){
        this.id = appointment.getId();

       this.doctorId = appointment.getDoctor().getId();

       this.hospitalId = appointment.getHospital().getId();

        this.serviceId = appointment.getServices().getId();

        this.date = parseDateToString(appointment.getDate());

        this.startTime = convertTimeToString(appointment.getStartTime().getTime());

        this.endTime = convertTimeToString(appointment.getEndTime().getTime());
    }

    public String convertTimeToString(long timeInMillis) {
        Instant instant = Instant.ofEpochMilli(timeInMillis);
        LocalTime localTime = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(3)).toLocalTime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return localTime.format(formatter);
    }

    public String parseDateToString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }
}
