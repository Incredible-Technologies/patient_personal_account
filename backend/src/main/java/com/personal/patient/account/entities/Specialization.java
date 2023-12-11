package com.personal.patient.account.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "specialization")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @ManyToOne(
            cascade = CascadeType.ALL, fetch = FetchType.LAZY
    )
    @JoinColumn(name="doctor_id")
    private Doctor doctor;

    @Override
    public String toString() {
        return "Specialization";
    }
}
