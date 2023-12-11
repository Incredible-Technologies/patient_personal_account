package com.personal.patient.account.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "hospital"
    )
    private List<Doctor> doctors = new ArrayList<>();

    @Column(name="opening_time")
    @Temporal(TemporalType.TIME)
    private Date openingTime;

    @Column(name="closing_time")
    @Temporal(TemporalType.TIME)
    private Date closingTime;

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "hospital"
    )
    private List<Appointment> appointments = new ArrayList<>();

    @Override
    public String toString() {
        return "Hospital";
    }
}
