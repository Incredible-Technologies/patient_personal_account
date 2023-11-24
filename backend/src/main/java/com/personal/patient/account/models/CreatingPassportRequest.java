package com.personal.patient.account.models;

import com.personal.patient.account.entities.Passport;
import com.personal.patient.account.models.enums.PassportGender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatingPassportRequest {
    private Long id;

    private String fullName;

    private PassportGender passportGender;

    private Date dateOfBirth;

    private String placeOfBirth;

    private Integer series;

    private Integer number;

    private Date dateOfIssue;

    private Integer divisionCode;

    private String issuedBy;

    //TO DO поменять убрать время
    public CreatingPassportRequest(Passport passport){
        this.id = passport.getId();

        this.fullName = passport.getFullName();

        this.passportGender = passport.getPassportGender();

        this.dateOfBirth = passport.getDateOfBirth();

        this.placeOfBirth = passport.getPlaceOfBirth();

        this.series = passport.getSeries();

        this.number = passport.getNumber();

        this.dateOfIssue = passport.getDateOfIssue();

        this.divisionCode = passport.getDivisionCode();

        this.issuedBy = passport.getIssuedBy();
    }
}
