package com.personal.patient.account.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="passport_file")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassportFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name = "originalFileName")
    private String originalFileName;

    @Column(name="size")
    private Long size;

    @Column(name="contentType")
    private String contentType;

    @Lob
    private byte[] bytes;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="passport_id")
    private Passport passport;
}
