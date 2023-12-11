package com.personal.patient.account.controllers;

import com.personal.patient.account.models.CreatingAppointmentResponse;
import com.personal.patient.account.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointment")
public class AppointmentController {
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
