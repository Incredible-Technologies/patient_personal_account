package com.personal.patient.account.service;

import com.personal.patient.account.entities.*;
import com.personal.patient.account.exceptions.NotFoundException;
import com.personal.patient.account.models.CreatingAppointmentRequest;
import com.personal.patient.account.models.CreatingAppointmentResponse;
import com.personal.patient.account.repositories.AppointmentRepository;
import com.personal.patient.account.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    private final DoctorService doctorService;

    private final HospitalService hospitalService;

    private final ServicesService servicesService;

    private final UserService userService;

    private final DateUtils dateUtils;

    public CreatingAppointmentRequest addAppointment(CreatingAppointmentResponse creatingAppointmentResponse, Principal principal){
        // решить задачу с свободным временем врача

        Doctor doctor = doctorService.findById(creatingAppointmentResponse.getDoctorId()).orElseThrow(
                () -> new NotFoundException("no doctor with such id: " + creatingAppointmentResponse.getDoctorId())
        );

        Hospital hospital = hospitalService.findById(creatingAppointmentResponse.getHospitalId()).orElseThrow(
                () -> new NotFoundException("no hospital with such id: " + creatingAppointmentResponse.getHospitalId())
        );

        Services service = servicesService.findById(creatingAppointmentResponse.getServiceId()).orElseThrow(
                () -> new NotFoundException("no service with such id: " + creatingAppointmentResponse.getServiceId())
        );

        User user = userService.getUserByPrincipal(principal);

        Appointment appointment = new Appointment();

        appointment.setServices(service);
        appointment.setDoctor(doctor);
        appointment.setHospital(hospital);
        appointment.setUser(user);

        Date startTime = dateUtils.parseStringToTime(creatingAppointmentResponse.getStartTime());

        appointment.setDate(dateUtils.parseStringToDate(creatingAppointmentResponse.getDate()));

        appointment.setStartTime(startTime);

        appointment.setEndTime(dateUtils.addMinutes(startTime, service.getDuration()));

        appointment = appointmentRepository.save(appointment);
        return new CreatingAppointmentRequest(appointment);
    }
}
