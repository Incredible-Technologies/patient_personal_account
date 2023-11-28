package com.personal.patient.account.controllers;

import com.personal.patient.account.models.CreatingHospitalResponse;
import com.personal.patient.account.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;

    @PostMapping("")
    public ResponseEntity<?> saveOrChangeProfile(@RequestBody CreatingHospitalResponse creatingHospitalResponse){
        return ResponseEntity.ok(hospitalService.addHospital(creatingHospitalResponse));
    }
}
