package com.personal.patient.account.models;

import com.personal.patient.account.entities.Profile;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor

public class ProfileRepresentation {
    private String firstName;

    private String middleName;

    private String lastName;

    private String address;

    private String dateOfBirth;

    private String phoneNumber;

    private String gender;

    public ProfileRepresentation(Profile userinfo){
        this.firstName = userinfo.getFirstName();

        this.middleName = userinfo.getMiddleName();

        this.lastName = userinfo.getLastName();

        this.address = userinfo.getAddress();

        if(userinfo.getDateOfBirth()!=null) {
            this.dateOfBirth = userinfo.getDateOfBirth().toString();
        }

        this.phoneNumber = userinfo.getPhoneNumber();

        this.gender = userinfo.getGender().getValue();
    }
}
