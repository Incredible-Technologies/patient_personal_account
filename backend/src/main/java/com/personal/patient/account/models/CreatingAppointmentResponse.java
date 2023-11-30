package com.personal.patient.account.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatingAppointmentResponse {
    private Long doctorId;

    private Long hospitalId;

    private Long serviceId;

    private String date;

    private String startTime;
}
