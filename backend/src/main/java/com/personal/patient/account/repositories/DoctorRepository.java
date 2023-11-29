package com.personal.patient.account.repositories;

import com.personal.patient.account.entities.Doctor;
import com.personal.patient.account.entities.Hospital;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    List<Doctor> findByHospital(Hospital hospital);
}
