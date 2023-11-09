package com.personal.patient.account.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="result_file")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultFile {
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
    @JoinColumn(name = "result_card_id")
    private ResultCard resultCard;
}
