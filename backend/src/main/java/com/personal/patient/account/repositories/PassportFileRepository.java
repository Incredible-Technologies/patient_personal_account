package com.personal.patient.account.repositories;

import com.personal.patient.account.entities.PassportFile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PassportFileRepository extends CrudRepository<PassportFile, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM PassportFile pf WHERE pf.id = :passportFileId")
    void myDeleteById(@Param("passportFileId") Long passportFileId);
}
