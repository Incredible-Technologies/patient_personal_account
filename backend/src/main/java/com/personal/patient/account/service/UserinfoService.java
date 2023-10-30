package com.personal.patient.account.service;

import com.personal.patient.account.entities.User;
import com.personal.patient.account.entities.Userinfo;
import com.personal.patient.account.models.UserInfoRepresentation;
import com.personal.patient.account.models.enums.Gender;
import com.personal.patient.account.repositories.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserinfoService {

    private final UserInfoRepository userInfoRepository;

    public void createOrChangeUserinfo(UserInfoRepresentation userInfoRequest, User user){
        Optional<Userinfo> existUserinfo = userInfoRepository.findByUser(user);
        Userinfo userinfo = existUserinfo.orElseGet(Userinfo::new);

        userinfo.setUser(user);

        userinfo.setFirstName(userInfoRequest.getFirstName());
        userinfo.setMiddleName(userInfoRequest.getMiddleName());
        userinfo.setLastName(userInfoRequest.getLastName());

        //To do
        // разобраться в атоматическом парсинге даты по анотации
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Date date = new Date();
        try {
            date = dateFormat.parse(userInfoRequest.getDateOfBirth());
        } catch (ParseException e) {
            // Ошибка парсинга даты
            e.printStackTrace();
        }
        userinfo.setDateOfBirth(date);
        userinfo.setPhoneNumber(userInfoRequest.getPhoneNumber());
        userinfo.setGender(Gender.fromValue(userInfoRequest.getGender()));
    }

    public Userinfo getUserinfoByUser(User user){
        return userInfoRepository.findByUser(user).orElseGet(Userinfo::new);
    }
}
