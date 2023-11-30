package com.personal.patient.account.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "services")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Services {
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

    @Column(name = "duration_in_minutes")
    private Integer duration;

    @OneToMany(
            cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            mappedBy = "services"
    )
    private List<Appointment> appointments = new ArrayList<>();
}
