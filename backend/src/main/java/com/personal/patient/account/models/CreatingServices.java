package com.personal.patient.account.models;

import com.personal.patient.account.entities.Services;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatingServices {
    private String name;

    private Integer duration;

    public CreatingServices(Services services){
        this.name = services.getName();
        this.duration = services.getDuration();
    }
}