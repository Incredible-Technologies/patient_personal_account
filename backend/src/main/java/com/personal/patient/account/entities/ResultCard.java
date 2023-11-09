package com.personal.patient.account.entities;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="resultCard")
@Data
public class ResultCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="date_of_make")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateOfMake;

    @Column(name="date_of_should_ready")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern= "dd-MM-yyyy")
    private Date dateOfShouldReady;

    @Column(name="date_of_delivered")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateOfDelivered;

    @Column(name="hospitalAddress")
    private String hospitalAddress;

    @OneToOne(
            mappedBy = "resultCard", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private ResultFile resultFile;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @Override
    public String toString() {
        return "ResultCard";
    }
}
