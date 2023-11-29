package com.personal.patient.account.models;

import com.personal.patient.account.entities.Passport;
import com.personal.patient.account.models.enums.PassportGender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatingPassportRequest {
    private Long id;

    private String fullName;

    private PassportGender passportGender;

    private String dateOfBirth;

    private String placeOfBirth;

    private Integer series;

    private Integer number;

    private String dateOfIssue;

    private Integer divisionCode;

    private String issuedBy;

    public CreatingPassportRequest(Passport passport){
        this.id = passport.getId();

        this.fullName = passport.getFullName();

        this.passportGender = passport.getPassportGender();

        this.dateOfBirth = parseDateToString(passport.getDateOfBirth());

        this.placeOfBirth = passport.getPlaceOfBirth();

        this.series = passport.getSeries();

        this.number = passport.getNumber();

        this.dateOfIssue = parseDateToString(passport.getDateOfIssue());

        this.divisionCode = passport.getDivisionCode();

        this.issuedBy = passport.getIssuedBy();
    }

    public String parseDateToString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
}
