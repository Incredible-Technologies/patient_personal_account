package com.personal.patient.account.controllers;

import com.personal.patient.account.exceptions.NotFoundException;
import com.personal.patient.account.models.CreatingDoctorResponse;
import com.personal.patient.account.models.CreatingHospitalResponse;
import com.personal.patient.account.models.CreatingServices;
import com.personal.patient.account.models.CreatingSpecialization;
import com.personal.patient.account.service.DoctorService;
import com.personal.patient.account.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hospitals")
public class HospitalController {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    private final HospitalService hospitalService;

    private final DoctorService doctorService;

    @PostMapping("")
    public ResponseEntity<?> addHospital(@RequestBody CreatingHospitalResponse creatingHospitalResponse){
        return ResponseEntity.ok(hospitalService.addHospital(creatingHospitalResponse));
    }

    @PostMapping("/doctor")
    public ResponseEntity<?> addDoctorToHospital(@RequestBody CreatingDoctorResponse creatingDoctorResponse){
        return ResponseEntity.ok(hospitalService.addDoctorToHospital(creatingDoctorResponse, creatingDoctorResponse.getHospitalId()));
    }

    @GetMapping("")
    public ResponseEntity<?> allHospital(){
        return ResponseEntity.ok(hospitalService.allHospitals());
    }

    @GetMapping("/doctors/{hospitalId}")
    public ResponseEntity<?> allHospitalsDoctor(@PathVariable Long hospitalId){
        return ResponseEntity.ok(hospitalService.allHospitalsDoctors(hospitalId));
    }

    @PostMapping("/doctor/services")
    public ResponseEntity<?> addServicesToDoctor(@RequestBody CreatingServices creatingServices,
                                                 @RequestParam("doctorId") Long doctorId){
        return ResponseEntity.ok(doctorService.addServices(creatingServices, doctorId));
    }
    @PostMapping("/doctor/specializations")
    public ResponseEntity<?> addSpecializationsToDoctor(@RequestBody CreatingSpecialization creatingSpecialization,
                                                 @RequestParam("doctorId") Long doctorId){
        return ResponseEntity.ok(doctorService.addSpecializations(creatingSpecialization, doctorId));
    }

    @GetMapping("/doctors")
    public ResponseEntity<?> allDoctors(){
        return ResponseEntity.ok(doctorService.allDoctors());
    }
}
