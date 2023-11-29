package com.personal.patient.account.service;

import com.personal.patient.account.entities.Doctor;
import com.personal.patient.account.entities.Hospital;
import com.personal.patient.account.entities.Services;
import com.personal.patient.account.entities.Specialization;
import com.personal.patient.account.exceptions.NotFoundException;
import com.personal.patient.account.models.CreatingDoctorRequest;
import com.personal.patient.account.models.CreatingServices;
import com.personal.patient.account.models.CreatingSpecialization;
import com.personal.patient.account.repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public CreatingDoctorRequest addServices(CreatingServices creatingServices, Long doctorId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(
                () -> new NotFoundException("No doctor with id: " + doctorId)
        );
        Services services = new Services();
        services.setDoctor(doctor);
        services.setName(creatingServices.getName());
        services.setDuration(creatingServices.getDuration());
        doctor.getServices().add(services);
        doctor = doctorRepository.save(doctor);
        return new CreatingDoctorRequest(doctor);
    }

    public CreatingDoctorRequest addSpecializations(CreatingSpecialization creatingSpecialization, Long doctorId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(
                () -> new NotFoundException("No doctor with id: " + doctorId)
        );
        Specialization specialization = new Specialization();
        specialization.setDoctor(doctor);
        specialization.setName(creatingSpecialization.getName());
        doctor.getSpecializations().add(specialization);
        doctor = doctorRepository.save(doctor);
        return new CreatingDoctorRequest(doctor);
    }

    public List<CreatingDoctorRequest> allDoctors(){
        return doctorRepository.findAll().stream().map(CreatingDoctorRequest::new).collect(Collectors.toList());
    }
}