package com.personal.patient.account.service;

import com.personal.patient.account.entities.User;
import com.personal.patient.account.entities.Userinfo;
import com.personal.patient.account.models.SaveAuthedUserInfoRequest;
import com.personal.patient.account.models.enums.Gender;
import com.personal.patient.account.repositories.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserinfoService {

    private final UserInfoRepository userInfoRepository;

    public ResponseEntity<?> createNewUserinfo(SaveAuthedUserInfoRequest userInfoRequest, User user){
        Userinfo userinfo = new Userinfo();
        userinfo.setUser(user);
        userinfo.setFirstName(userInfoRequest.getFirstName());
        userinfo.setMiddleName(userInfoRequest.getMiddleName());
        userinfo.setLastName(userInfoRequest.getLastName());
        userinfo.setDateBirthDay(userInfoRequest.getDateBirthDay());
        userinfo.setPhoneNumber(userInfoRequest.getPhoneNumber());
        userinfo.setGender(Gender.fromValue(userInfoRequest.getGender()));
        return ResponseEntity.ok(userInfoRepository.save(userinfo));
    }
}
