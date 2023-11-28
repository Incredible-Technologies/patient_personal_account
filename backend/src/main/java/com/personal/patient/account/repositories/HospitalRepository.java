package com.personal.patient.account.repositories;

import com.personal.patient.account.entities.Hospital;
import org.springframework.data.repository.CrudRepository;

public interface HospitalRepository extends CrudRepository<Hospital, Integer> {
}
