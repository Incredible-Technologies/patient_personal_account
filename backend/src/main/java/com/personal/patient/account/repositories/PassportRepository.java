package com.personal.patient.account.repositories;

import com.personal.patient.account.entities.Passport;
import com.personal.patient.account.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PassportRepository extends CrudRepository<Passport, Long> {
    Optional<Passport> findByUser(User user);
}
