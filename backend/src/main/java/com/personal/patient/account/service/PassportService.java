package com.personal.patient.account.service;


import com.personal.patient.account.entities.Passport;
import com.personal.patient.account.exceptions.NotFoundException;
import com.personal.patient.account.models.CreatingPassportRequest;
import com.personal.patient.account.models.CreatingPassportResponse;
import com.personal.patient.account.models.enums.PassportGender;
import com.personal.patient.account.repositories.PassportRepository;
import com.personal.patient.account.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class PassportService {

    private final PassportRepository passportRepository;

    private final UserService userService;

    private final DateUtils dateUtils;

    public void fillPassport(Passport passport, CreatingPassportResponse creatingPassportResponse,
                             Principal principal){
        passport.setUser(userService.getUserByPrincipal(principal));
        passport.setFullName(creatingPassportResponse.getFullName());
        passport.setPassportGender(PassportGender.fromValue(creatingPassportResponse.getPassportGender()));
        passport.setDateOfBirth(dateUtils.parseStringToDate(creatingPassportResponse.getDateOfBirth()));
        passport.setPlaceOfBirth(creatingPassportResponse.getPlaceOfBirth());
        passport.setSeries(creatingPassportResponse.getSeries());
        passport.setNumber(creatingPassportResponse.getNumber());
        passport.setDateOfIssue(dateUtils.parseStringToDate(creatingPassportResponse.getDateOfIssue()));
        passport.setDivisionCode(creatingPassportResponse.getDivisionCode());
        passport.setIssuedBy(creatingPassportResponse.getIssuedBy());
    }

    public CreatingPassportRequest createPassport(CreatingPassportResponse creatingPassportResponse, Principal principal){
        Passport passport = new Passport();
        fillPassport(passport, creatingPassportResponse, principal);
        passport = passportRepository.save(passport);
        return new CreatingPassportRequest(passport);
    };


    public CreatingPassportRequest getPassport(Principal principal){
        Passport passport = passportRepository.findByUser(userService.getUserByPrincipal(principal))
                .orElseThrow(()-> new NotFoundException("user with email " + principal.getName() + " did not have passport"));
        return new CreatingPassportRequest(passport);
    }

    public CreatingPassportRequest changePassport(CreatingPassportResponse creatingPassportResponse, Principal principal){
        Passport passport = passportRepository.findByUser(userService.getUserByPrincipal(principal))
                .orElseThrow(()-> new NotFoundException("user with email " + principal.getName() + " did not have passport"));
        fillPassport(passport, creatingPassportResponse, principal);
        passport = passportRepository.save(passport);
        return new CreatingPassportRequest(passport);
    }

}
