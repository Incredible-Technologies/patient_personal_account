package com.personal.patient.account.entities;


import com.personal.patient.account.models.enums.PassportGender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(
        name="passport",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "user_id")
        }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="full_name")
    private String fullName;

    @Enumerated(EnumType.STRING)
    private PassportGender passportGender;

    @Column(name="date_of_birt")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateOfBirth;

    @Column(name="place_of_birth")
    private String placeOfBirth;

    @Column(name="series")
    private Integer series;

    @Column(name="number")
    private Integer number;

    @Column(name="date_of_issue")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateOfIssue;

    @Column(name="division_code")
    private Integer divisionCode;

    @Column(name="issued_by")
    private String issuedBy;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(
            fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            mappedBy = "passport"
    )
    private PassportFile passportFile;

}