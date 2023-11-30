package com.personal.patient.account.service;

import com.personal.patient.account.entities.Services;
import com.personal.patient.account.repositories.ServicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ServicesService {
    private final ServicesRepository servicesRepository;

    public void save(Services services){
        servicesRepository.save(services);
    }

    public Optional<Services> findById(Long id){
        return servicesRepository.findById(id);
    };
}
