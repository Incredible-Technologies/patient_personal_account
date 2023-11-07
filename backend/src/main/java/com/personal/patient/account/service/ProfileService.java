package com.personal.patient.account.service;

import com.personal.patient.account.entities.User;
import com.personal.patient.account.entities.Profile;
import com.personal.patient.account.models.ProfileRepresentation;
import com.personal.patient.account.models.enums.Gender;
import com.personal.patient.account.repositories.ProfileRepository;
import com.personal.patient.account.repositories.UserRepository;
import com.personal.patient.account.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    private final UserRepository userRepository;

    private final DateUtils dateUtils;

    public void createOrChangeProfile(ProfileRepresentation profileRequest, User user){
        Optional<Profile> existProfile = profileRepository.findByUser(user);
        Profile profile = existProfile.orElseGet(Profile::new);

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
        userRepository.save(user);
    }

    public Profile getProfileByUser(User user){
        return profileRepository.findByUser(user).orElseGet(Profile::new);
    }
}
