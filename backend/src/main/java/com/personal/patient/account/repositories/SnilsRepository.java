package com.personal.patient.account.repositories;

import com.personal.patient.account.entities.Snils;
import com.personal.patient.account.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SnilsRepository extends CrudRepository<Snils, Long> {
    Optional<Snils> findByUser(User user);
}
