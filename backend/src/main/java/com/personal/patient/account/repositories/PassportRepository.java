package com.personal.patient.account.repositories;

import com.personal.patient.account.entities.Passport;
import org.springframework.data.repository.CrudRepository;

public interface PassportRepository extends CrudRepository<Passport, Long> {
}
