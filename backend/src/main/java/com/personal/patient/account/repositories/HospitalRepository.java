package com.personal.patient.account.repositories;

import com.personal.patient.account.entities.Hospital;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HospitalRepository extends CrudRepository<Hospital, Integer> {
    Optional<Hospital> findById(Long id);
}
