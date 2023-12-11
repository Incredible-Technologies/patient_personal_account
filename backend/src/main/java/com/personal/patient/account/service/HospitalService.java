package com.personal.patient.account.service;

import com.personal.patient.account.entities.Doctor;
import com.personal.patient.account.entities.Hospital;
import com.personal.patient.account.entities.Services;
import com.personal.patient.account.entities.Specialization;
import com.personal.patient.account.exceptions.NotFoundException;
import com.personal.patient.account.models.CreatingDoctorRequest;
import com.personal.patient.account.models.CreatingDoctorResponse;
import com.personal.patient.account.models.CreatingHospitalRequest;
import com.personal.patient.account.models.CreatingHospitalResponse;
import com.personal.patient.account.models.enums.Gender;
import com.personal.patient.account.repositories.HospitalRepository;
import com.personal.patient.account.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;

    private final DoctorService doctorService;

    private final DateUtils dateUtils;

    public void save(Hospital hospital){
        hospitalRepository.save(hospital);
    }

    public Optional<Hospital> findById(Long id){
        return hospitalRepository.findById(id);
    }

    public CreatingHospitalRequest addHospital(CreatingHospitalResponse creatingHospitalResponse){
        Hospital hospital = new Hospital();
        hospital.setName(creatingHospitalResponse.getName());
        hospital.setAddress(creatingHospitalResponse.getAddress());
        hospital.setOpeningTime(dateUtils.parseStringToTime(creatingHospitalResponse.getOpeningTime()));
        hospital.setClosingTime(dateUtils.parseStringToTime(creatingHospitalResponse.getClosingTime()));
        hospital = hospitalRepository.save(hospital);
        return new CreatingHospitalRequest(hospital);
    }

    public List<CreatingHospitalRequest> allHospitals(){
        return hospitalRepository.findAll().stream().map(CreatingHospitalRequest::new).collect(Collectors.toList());
    }

    public List<CreatingDoctorRequest> allHospitalsDoctors(Long hospitalId){
        Hospital hospital = hospitalRepository.findById(hospitalId).orElseThrow(
                () -> new NotFoundException("No passport file with id: " + hospitalId)
        );
        return doctorService.findByHospital(hospital).stream().map(CreatingDoctorRequest::new).collect(Collectors.toList());
    }

    public CreatingDoctorRequest addDoctorToHospital(CreatingDoctorResponse creatingDoctorResponse, Long hospitalId){
        Hospital hospital = hospitalRepository.findById(hospitalId).orElseThrow(
                () -> new NotFoundException("No hospital file with id: " + hospitalId)
        );
        final Doctor doctor = new Doctor();
//        doctor.setHospital(hospital);
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
        doctor.setStartTime(dateUtils.parseStringToTime(creatingDoctorResponse.getStartTime()));
        doctor.setEndTime(dateUtils.parseStringToTime(creatingDoctorResponse.getEndTime()));
        doctor.setDateOfBirth(dateUtils.parseStringToDate(creatingDoctorResponse.getDateOfBirth()));
        doctor.setGender(Gender.fromValue(creatingDoctorResponse.getGender()));
        Doctor doctor1 = doctorService.save(doctor);
        return new CreatingDoctorRequest(doctor1);
    }
}
