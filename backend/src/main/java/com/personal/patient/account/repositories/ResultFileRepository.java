package com.personal.patient.account.repositories;

import com.personal.patient.account.entities.ResultCard;
import com.personal.patient.account.entities.ResultFile;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ResultFileRepository extends CrudRepository<ResultFile, Long> {
    Optional<ResultFile> findByResultCard(ResultCard resultCard);
}
