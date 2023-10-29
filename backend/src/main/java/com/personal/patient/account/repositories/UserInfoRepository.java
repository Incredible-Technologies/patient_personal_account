package com.personal.patient.account.repositories;

import com.personal.patient.account.entities.User;
import com.personal.patient.account.entities.Userinfo;
import org.springframework.data.repository.CrudRepository;

public interface UserInfoRepository extends CrudRepository<Userinfo, Long> {
}
