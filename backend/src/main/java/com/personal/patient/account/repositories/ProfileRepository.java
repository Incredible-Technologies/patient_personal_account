package com.personal.patient.account.repositories;

import com.personal.patient.account.entities.User;
import com.personal.patient.account.entities.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
    Optional<Profile> findByUser(User user);
}
