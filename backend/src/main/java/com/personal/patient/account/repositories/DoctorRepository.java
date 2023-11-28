package com.personal.patient.account.repositories;

import com.personal.patient.account.entities.Doctor;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<Doctor, Integer> {
}
