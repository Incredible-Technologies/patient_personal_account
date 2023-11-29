package com.personal.patient.account.entities;

import com.personal.patient.account.models.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "doctor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="firstName")
    private String firstName;

    @Column(name="middleName")
    private String middleName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="date_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne(
            cascade = CascadeType.ALL, fetch = FetchType.LAZY
    )
    @JoinColumn(name="hospital_id")
    private Hospital hospital;

    @OneToMany(
            cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "doctor"
    )
    private List<Specialization> specializations = new ArrayList<>();

    @OneToMany(
            cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "doctor"
    )
    private List<Services> services = new ArrayList<>();


    @Column(name="start_time")
    @Temporal(TemporalType.TIME)
    private Date startTime;

    @Column(name="end_time")
    @Temporal(TemporalType.TIME)
    private Date endTime;

    @Override
    public String toString() {
        return "Doctor";
    }
}
