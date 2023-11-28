package com.personal.patient.account.models;

import com.personal.patient.account.entities.Specialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatingSpecialization {
    private String name;

    public CreatingSpecialization(Specialization specialization){
        this.name = specialization.getName();
    }
}