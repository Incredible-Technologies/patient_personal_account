package com.personal.patient.account.models;

import lombok.Data;
@Data
public class FullProfileRepresentation {
    private Long id;
    private String firstName;

    private String middleName;

    private String lastName;

    private String address;

    private String dateOfBirth;

    private String phoneNumber;

    private String gender;

    public FullProfileRepresentation(ProfileRepresentation request, Long userId){
        this.firstName = request.getFirstName();
        this.middleName= request.getMiddleName();
        this.lastName = request.getLastName();
        this.address = request.getAddress();
        this.dateOfBirth = request.getDateOfBirth();
        this.phoneNumber = request.getPhoneNumber();
        this.gender = request.getGender();
        this.id = userId;
    }
}