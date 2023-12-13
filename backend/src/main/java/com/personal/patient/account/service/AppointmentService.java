package com.personal.patient.account.service;

import com.personal.patient.account.entities.*;
import com.personal.patient.account.exceptions.NotFoundException;
import com.personal.patient.account.exceptions.OverAppointmentException;
import com.personal.patient.account.models.CreatingAppointmentRequest;
import com.personal.patient.account.models.CreatingAppointmentResponse;
import com.personal.patient.account.repositories.AppointmentRepository;
import com.personal.patient.account.utils.DateUtils;
import com.personal.patient.account.utils.Period;
import com.personal.patient.account.utils.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    private final DoctorService doctorService;

    private final HospitalService hospitalService;

    private final ServicesService servicesService;

    private final UserService userService;

    private final DateUtils dateUtils;

    public List<Appointment> getByDoctorAndDate(Doctor doctor, Date date){
        return appointmentRepository.findByDoctorAndDate(doctor, date);
    }

    public CreatingAppointmentRequest addAppointment(CreatingAppointmentResponse creatingAppointmentResponse, Principal principal){
        // решить задачу с свободным временем врача

        User user = userService.getUserByPrincipal(principal);

        Doctor doctor = doctorService.findById(creatingAppointmentResponse.getDoctorId()).orElseThrow(
                () -> new NotFoundException("no doctor with such id: " + creatingAppointmentResponse.getDoctorId())
        );

        Hospital hospital = hospitalService.findById(creatingAppointmentResponse.getHospitalId()).orElseThrow(
                () -> new NotFoundException("no hospital with such id: " + creatingAppointmentResponse.getHospitalId())
        );

        Services service = servicesService.findById(creatingAppointmentResponse.getServiceId()).orElseThrow(
                () -> new NotFoundException("no service with such id: " + creatingAppointmentResponse.getServiceId())
        );

        Appointment appointment = new Appointment();


        Date startTime = dateUtils.parseStringToTime(creatingAppointmentResponse.getStartTime());

        appointment.setStartTime(startTime);

        appointment.setEndTime(dateUtils.addMinutes(startTime, service.getDuration()));

        Schedule freeTimes = getDoctorFreeTime(doctor,hospital, dateUtils.parseStringToDate(creatingAppointmentResponse.getDate()));

        boolean successAdding = freeTimes.addEmploymentPeriod(
                new Period(
                        dateUtils.dateToTime(appointment.getStartTime()),
                        dateUtils.dateToTime(appointment.getEndTime())
                )
        );

        if(!successAdding){
            throw new OverAppointmentException("Запись в это время уже занята, перезапустите страницу и выберите другое время");
        }

        appointment.setServices(service);
        appointment.setDoctor(doctor);
        appointment.setHospital(hospital);
        appointment.setUser(user);


        appointment.setDate(dateUtils.parseStringToDate(creatingAppointmentResponse.getDate()));

        appointment = appointmentRepository.save(appointment);
        return new CreatingAppointmentRequest(appointment);
    }

    public List<CreatingAppointmentRequest> getAllUserAppointment(Principal principal){
        User user = userService.getUserByPrincipal(principal);
        List<Appointment> list = appointmentRepository.findByUser(user);
        return list.stream().map(CreatingAppointmentRequest::new).collect(Collectors.toList());
    }

    public List<CreatingAppointmentRequest> getAllUserAppointmentByDate(Principal principal, String stringDate){
        Date date = dateUtils.parseStringToDate(stringDate);
        User user = userService.getUserByPrincipal(principal);
        List<Appointment> list = appointmentRepository.findByUserAndDate(user, date);
        return list.stream().map(CreatingAppointmentRequest::new).collect(Collectors.toList());
    }

    public List<CreatingAppointmentRequest> getAllDoctorAppointmentByDate(String stringDate, Long doctorId){

        Doctor doctor = doctorService.findById(doctorId).orElseThrow(
                () -> new NotFoundException("no doctor with such id: " + doctorId)
        );

        Date date = dateUtils.parseStringToDate(stringDate);
        List<Appointment> list = appointmentRepository.findByDoctorAndDate(doctor, date);
        return list.stream().map(CreatingAppointmentRequest::new).collect(Collectors.toList());
    }

    public List<Period> getAllDoctorFreeTimeByDate(Long doctorId, String stringDate){
        Date date = dateUtils.parseStringToDate(stringDate);
        Doctor doctor = doctorService.findById(doctorId).orElseThrow(
                () -> new NotFoundException("no doctor with such id: " + doctorId)
        );
        Hospital hospital = doctor.getHospital();
        List<Appointment> list = appointmentRepository.findByDoctorAndDate(doctor, date);
        return getDoctorFreeTime(doctor, hospital, date).getPeriods();
    }

    public List<CreatingAppointmentRequest> getAllUserComingAppointment(Principal principal){
        User user = userService.getUserByPrincipal(principal);
        Date date = new Date();
        List<Appointment> listAfter = appointmentRepository.findAppointmentsByDateAfter(date, user);
        List<Appointment> listEquals = appointmentRepository.findAppointmentsByDateEquals(date, user);
        listEquals.forEach((element) ->{
                if(dateUtils.dateToTime(element.getStartTime()).isAfter(dateUtils.dateToTime(date))){
                    listAfter.add(element);
                }
            }
        );
        return listAfter.stream().map(CreatingAppointmentRequest::new).collect(Collectors.toList());
    }

    public List<CreatingAppointmentRequest> getAllUserPastAppointment(Principal principal){
        User user = userService.getUserByPrincipal(principal);
        Date date = new Date();
        List<Appointment> listBefore = appointmentRepository.findAppointmentsByDateBefore(date, user);
        List<Appointment> listEquals = appointmentRepository.findAppointmentsByDateEquals(date, user);
        listEquals.forEach((element) ->{
                    if(dateUtils.dateToTime(element.getStartTime()).isBefore(dateUtils.dateToTime(date))){
                        listBefore.add(element);
                    }
                }
        );
        return listBefore.stream().map(CreatingAppointmentRequest::new).collect(Collectors.toList());
    }

    public List<CreatingAppointmentRequest> getAllDoctorAppointment(Long doctorId){
        Doctor doctor = doctorService.findById(doctorId).orElseThrow(
                () -> new NotFoundException("no doctor with such id: " + doctorId)
        );
        List<Appointment> list = appointmentRepository.findByDoctor(doctor);
        return list.stream().map(CreatingAppointmentRequest::new).collect(Collectors.toList());
    }

    public List<Appointment> getAllDoctorsAppointmentsByDate(Doctor doctor, Date date){
        return appointmentRepository.findByDoctorAndDate(doctor,date);
    }

    public Schedule getDoctorFreeTime(Doctor doctor, Hospital hospital, Date date){
        Schedule freeTimes = new Schedule(
                new Period(
                        dateUtils.dateToTime(hospital.getOpeningTime()),
                        dateUtils.dateToTime(hospital.getClosingTime())
                )
        );

        List<Period> busyPeriods = getByDoctorAndDate(doctor, date)
                .stream().map((element) ->new Period(
                        dateUtils.dateToTime(element.getStartTime()),
                        dateUtils.dateToTime(element.getEndTime())
                )).collect(Collectors.toList());

        busyPeriods.forEach(freeTimes::addEmploymentPeriod);
        return freeTimes;
    }
}
