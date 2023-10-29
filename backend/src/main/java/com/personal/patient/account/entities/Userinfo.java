package com.personal.patient.account.entities;

import com.personal.patient.account.models.enums.Gender;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Userinfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="firstName")
    private String firstName;

    @Column(name="middleName")
    private String middleName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="dateBirthDay")
    private String dateBirthDay;

    @Column(name="phoneNumber")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;
}
