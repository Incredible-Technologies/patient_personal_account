package com.personal.patient.account.service;

import com.personal.patient.account.entities.Doctor;
import com.personal.patient.account.entities.Hospital;
import com.personal.patient.account.entities.Services;
import com.personal.patient.account.entities.Specialization;
import com.personal.patient.account.exceptions.NotFoundException;
import com.personal.patient.account.models.CreatingDoctorRequest;
import com.personal.patient.account.models.CreatingDoctorResponse;
import com.personal.patient.account.models.enums.Gender;
import com.personal.patient.account.repositories.DoctorRepository;
import com.personal.patient.account.utils.DateUtils;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public Doctor save(Doctor doctor){
        return doctorRepository.save(doctor);
    }

    public List<Doctor> findByHospital(Hospital hospital){
        return doctorRepository.findByHospital(hospital);
    }
}