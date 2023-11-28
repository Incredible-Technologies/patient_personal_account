package com.personal.patient.account.service;

import com.personal.patient.account.entities.Services;
import com.personal.patient.account.repositories.ServicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ServicesService {
    private final ServicesRepository servicesRepository;

    public void save(Services services){
        servicesRepository.save(services);
    }
}
