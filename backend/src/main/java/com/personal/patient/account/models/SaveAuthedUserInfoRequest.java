package com.personal.patient.account.models;

import com.personal.patient.account.entities.User;
import com.personal.patient.account.models.enums.Gender;
import lombok.Data;

import javax.persistence.*;

@Data
public class SaveAuthedUserInfoRequest {
    private String firstName;

    private String middleName;

    private String lastName;

    private String dateBirthDay;

    private String phoneNumber;

    private String gender;
}
