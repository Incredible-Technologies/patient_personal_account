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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;

    private final HospitalService hospitalService;

    private final DateUtils dateUtils;

    public void save(Doctor doctor){
        doctorRepository.save(doctor);
    }

    public CreatingDoctorRequest addDoctorToHospital(CreatingDoctorResponse creatingDoctorResponse, Long hospitalId){
        Hospital hospital = hospitalService.findById(hospitalId).orElseThrow(
                () -> new NotFoundException("No hospital file with id: " + hospitalId)
        );
        final Doctor doctor = new Doctor();
        doctor.setHospital(hospital);
        List<Services> services = creatingDoctorResponse.getServices().stream().map((element)->{
            Services services1 = new Services();
            services1.setDoctor(doctor);
            services1.setName(element.getName());
            services1.setDuration(element.getDuration());
            return services1;
        }).collect(Collectors.toList());
        doctor.setServices(services);
        List<Specialization> specializations = creatingDoctorResponse.getSpecializations().stream().map((element)->{
            Specialization specialization = new Specialization();
            specialization.setDoctor(doctor);
            specialization.setName(element.getName());
            return specialization;
        }).collect(Collectors.toList());
        doctor.setSpecializations(specializations);
        doctor.setFirstName(creatingDoctorResponse.getFirstName());
        doctor.setMiddleName(creatingDoctorResponse.getMiddleName());
        doctor.setLastName(creatingDoctorResponse.getLastName());
        doctor.setDateOfBirth(dateUtils.parseStringToDate(creatingDoctorResponse.getDateOfBirth()));
        doctor.setGender(Gender.fromValue(creatingDoctorResponse.getGender()));
        Doctor doctor1 = doctorRepository.save(doctor);
        return new CreatingDoctorRequest(doctor1);
    }
}
