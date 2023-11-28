package com.personal.patient.account.controllers;

import com.personal.patient.account.models.CreatingDoctorResponse;
import com.personal.patient.account.models.CreatingHospitalResponse;
import com.personal.patient.account.service.DoctorService;
import com.personal.patient.account.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;

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
}
