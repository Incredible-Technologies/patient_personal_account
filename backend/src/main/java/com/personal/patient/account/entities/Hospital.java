package com.personal.patient.account.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "hospital")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    @OneToMany(
            cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            mappedBy = "hospital"
    )
    private List<Doctor> doctors = new ArrayList<>();

    @Column(name="opening_time")
    @Temporal(TemporalType.TIME)
    private Date openingTime;

    @Column(name="closing_time")
    @Temporal(TemporalType.TIME)
    private Date closingTime;

    @Override
    public String toString() {
        return "Hospital";
    }
}
