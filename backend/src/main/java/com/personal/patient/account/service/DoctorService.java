package com.personal.patient.account.service;

import com.personal.patient.account.entities.Doctor;
import com.personal.patient.account.repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public void save(Doctor doctor){
        doctorRepository.save(doctor);
    }
}
