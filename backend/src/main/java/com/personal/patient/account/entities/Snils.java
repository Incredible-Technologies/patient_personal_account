package com.personal.patient.account.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(
    name="snils",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "user_id")
        }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Snils {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="number")
    private Long number;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString(){
        return "snils";
    }
}
