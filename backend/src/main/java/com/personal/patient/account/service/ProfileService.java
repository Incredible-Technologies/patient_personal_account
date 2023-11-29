package com.personal.patient.account.service;

import com.personal.patient.account.entities.User;
import com.personal.patient.account.entities.Profile;
import com.personal.patient.account.exceptions.AlreadyExistException;
import com.personal.patient.account.exceptions.NotFoundException;
import com.personal.patient.account.models.FullProfileRepresentation;
import com.personal.patient.account.models.ProfileRepresentation;
import com.personal.patient.account.models.enums.Gender;
import com.personal.patient.account.repositories.ProfileRepository;
import com.personal.patient.account.repositories.UserRepository;
import com.personal.patient.account.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    private final UserService userService;

    private final DateUtils dateUtils;

    public FullProfileRepresentation createProfile(ProfileRepresentation profileRequest, Principal principal){
        User user = userService.getUserByPrincipal(principal);
        if(profileRepository.findByUser(user).isPresent()){
            throw new AlreadyExistException("user with email " + user.getEmail() + " alredy have profile");
        }
        Profile profile = new Profile();

        profile.setUser(user);

        profile.setFirstName(profileRequest.getFirstName());
        profile.setMiddleName(profileRequest.getMiddleName());
        profile.setLastName(profileRequest.getLastName());

        Date date = dateUtils.parseStringToDate(profileRequest.getDateOfBirth());
        profile.setDateOfBirth(date);
        profile.setPhoneNumber(profileRequest.getPhoneNumber());
        profile.setGender(Gender.fromValue(profileRequest.getGender()));
        profile.setAddress(profileRequest.getAddress());
        profileRepository.save(profile);
        user.setProfile(profile);
        userService.save(user);
        return new FullProfileRepresentation(profileRequest, user.getId());
    }

    public FullProfileRepresentation changeProfile(ProfileRepresentation profileRequest, Principal principal){
        User user = userService.getUserByPrincipal(principal);
        Profile profile = profileRepository.findByUser(user).orElseThrow(
                () ->new NotFoundException("user with email " + user.getEmail() + " did not have profile")
        );

        profile.setUser(user);

        profile.setFirstName(profileRequest.getFirstName());
        profile.setMiddleName(profileRequest.getMiddleName());
        profile.setLastName(profileRequest.getLastName());

        Date date = dateUtils.parseStringToDate(profileRequest.getDateOfBirth());
        profile.setDateOfBirth(date);
        profile.setPhoneNumber(profileRequest.getPhoneNumber());
        profile.setGender(Gender.fromValue(profileRequest.getGender()));
        profile.setAddress(profileRequest.getAddress());
        profileRepository.save(profile);
        user.setProfile(profile);
        userService.save(user);
        return new FullProfileRepresentation(profileRequest, user.getId());
    }

    public Profile getProfileByUser(User user){
        return profileRepository.findByUser(user).orElseGet(Profile::new);
    }
}
