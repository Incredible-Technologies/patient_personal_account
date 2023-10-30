package com.personal.patient.account.repositories;

import com.personal.patient.account.entities.User;
import com.personal.patient.account.entities.Userinfo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserInfoRepository extends CrudRepository<Userinfo, Long> {
    Optional<Userinfo> findByUser(User user);
}
