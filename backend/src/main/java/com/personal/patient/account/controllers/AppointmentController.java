package com.personal.patient.account.controllers;

import com.personal.patient.account.exceptions.NotFoundException;
import com.personal.patient.account.exceptions.OverAppointmentException;
import com.personal.patient.account.models.CreatingAppointmentResponse;
import com.personal.patient.account.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointment")
public class AppointmentController {

    @ExceptionHandler(OverAppointmentException.class)
    protected ResponseEntity<Object> handleOverAppointmentException(OverAppointmentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    private final AppointmentService appointmentService;

    @PostMapping("")
    public ResponseEntity<?> addAppointment(@RequestBody CreatingAppointmentResponse creatingAppointmentResponse
            , Principal principal){
        return ResponseEntity.ok(appointmentService.addAppointment(creatingAppointmentResponse, principal));
    }

    @GetMapping("/all")
    public ResponseEntity<?> allAppointment(Principal principal){
        return ResponseEntity.ok(appointmentService.getAllUserAppointment(principal));
    }

    @GetMapping("")
    public ResponseEntity<?> allAppointment(@RequestParam("date") String date, Principal principal){
        return ResponseEntity.ok(appointmentService.getAllUserAppointmentByDate(principal, date));
    }
    @GetMapping("/doctor")
    public ResponseEntity<?> allAppointment(@RequestParam("date") String date, @RequestParam("doctorId") Long doctorId, Principal principal){
        return ResponseEntity.ok(appointmentService.getAllDoctorAppointmentByDate(date, doctorId));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<?> allAppointment(@PathVariable Long doctorId, Principal principal){
        return ResponseEntity.ok(appointmentService.getAllDoctorAppointment(doctorId));
    }

    @GetMapping("/doctor/free-time")
    public ResponseEntity<?> getDoctorFreeTime(@RequestParam("date") String date, @RequestParam("doctorId") Long doctorId, Principal principal){
        return ResponseEntity.ok(appointmentService.getAllDoctorFreeTimeByDate(doctorId,date));
    }

    @GetMapping("/coming")
    public ResponseEntity<?> getUserComingAppointment(Principal principal){
        return ResponseEntity.ok(appointmentService.getAllUserComingAppointment(principal));
    }

    @GetMapping("/past")
    public ResponseEntity<?> getUserPastAppointment(Principal principal){
        return ResponseEntity.ok(appointmentService.getAllUserPastAppointment(principal));
    }
}
