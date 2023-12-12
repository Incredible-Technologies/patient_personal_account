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
}
