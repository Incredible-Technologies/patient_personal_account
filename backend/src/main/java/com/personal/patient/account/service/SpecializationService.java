package com.personal.patient.account.service;

import com.personal.patient.account.entities.Specialization;
import com.personal.patient.account.repositories.SpecializationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SpecializationService {
    private final SpecializationRepository specializationRepository;

    public void save(Specialization specialization){
        specializationRepository.save(specialization);
    }
}
