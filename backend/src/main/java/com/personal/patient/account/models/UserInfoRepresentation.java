package com.personal.patient.account.models;

import com.personal.patient.account.entities.Userinfo;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserInfoRepresentation {
    private String firstName;

    private String middleName;

    private String lastName;

    private String dateOfBirth;

    private String phoneNumber;

    private String gender;

    public UserInfoRepresentation(Userinfo userinfo){
        this.firstName = userinfo.getFirstName();

        this.middleName = userinfo.getMiddleName();

        this.lastName = userinfo.getLastName();

        this.dateOfBirth = userinfo.getDateOfBirth().toString();

        this.phoneNumber = userinfo.getPhoneNumber();

        this.gender = userinfo.getGender().getValue();
    }
}
