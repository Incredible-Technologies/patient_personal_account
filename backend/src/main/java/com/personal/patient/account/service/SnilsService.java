package com.personal.patient.account.service;

import com.personal.patient.account.entities.Snils;
import com.personal.patient.account.exceptions.NotFoundException;
import com.personal.patient.account.models.CreatingSnilsRequest;
import com.personal.patient.account.models.CreatingSnilsResponse;
import com.personal.patient.account.repositories.SnilsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class SnilsService {

    private final SnilsRepository snilsRepository;

    private final UserService userService;

    public CreatingSnilsRequest createSnils(CreatingSnilsResponse creatingSnilsResponse, Principal principal){
        Snils snils = new Snils();
        snils.setUser(userService.getUserByPrincipal(principal));
        snils.setNumber(creatingSnilsResponse.getNumber());
        snils = snilsRepository.save(snils);
        return new CreatingSnilsRequest(snils.getId(), snils.getNumber());
    };

    public CreatingSnilsResponse getSnils(Principal principal){
        Snils snils = snilsRepository.findByUser(userService.getUserByPrincipal(principal))
                .orElseThrow(()-> new NotFoundException("user with email " + principal.getName() + " did not have snils"));
        return new CreatingSnilsResponse(snils.getNumber());
    }
}
