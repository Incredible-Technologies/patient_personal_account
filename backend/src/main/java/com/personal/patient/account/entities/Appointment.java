package com.personal.patient.account.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "appointment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @ManyToOne(
            cascade = CascadeType.ALL, fetch = FetchType.LAZY
    )
    @JoinColumn(name="hospital_id")
    private Hospital hospital;

    @ManyToOne(
            cascade = CascadeType.ALL, fetch = FetchType.LAZY
    )
    @JoinColumn(name="doctor_id")
    private Doctor doctor;

    @ManyToOne(
            cascade = CascadeType.ALL, fetch = FetchType.LAZY
    )
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(
            cascade = CascadeType.ALL, fetch = FetchType.LAZY
    )
    @JoinColumn(name="services_id")
    private Services services;

    @Column(name="date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name="start_time")
    @Temporal(TemporalType.TIME)
    private Date startTime;

    @Column(name="end_time")
    @Temporal(TemporalType.TIME)
    private Date endTime;
}
