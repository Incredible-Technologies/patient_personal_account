package com.personal.patient.account.repositories;

import com.personal.patient.account.entities.ResultCard;
import com.personal.patient.account.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResultCardRepository extends CrudRepository<ResultCard, Long> {
    List<ResultCard> findByUserOrderByDateOfShouldReadyAsc(User user);
    List<ResultCard> findByUserAndDateOfDeliveredIsNotNullOrderByDateOfShouldReadyAsc(User user);
    List<ResultCard> findByUserAndDateOfDeliveredIsNullOrderByDateOfShouldReadyAsc(User user);
}
