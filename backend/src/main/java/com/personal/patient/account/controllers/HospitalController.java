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

    private final DoctorService doctorService;

    @PostMapping("")
    public ResponseEntity<?> addHospital(@RequestBody CreatingHospitalResponse creatingHospitalResponse){
        return ResponseEntity.ok(hospitalService.addHospital(creatingHospitalResponse));
    }

    @PostMapping("/doctor")
    public ResponseEntity<?> addDoctorToHospital(@RequestBody CreatingDoctorResponse creatingDoctorResponse,
                                                 @RequestParam("hospitalId") Long hospitalId){
        return ResponseEntity.ok(doctorService.addDoctorToHospital(creatingDoctorResponse, hospitalId));
    }

    @GetMapping("")
    public ResponseEntity<?> allHospital(){
        return ResponseEntity.ok(hospitalService.allHospitals());
    }
}
