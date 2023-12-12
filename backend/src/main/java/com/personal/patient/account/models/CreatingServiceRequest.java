package com.personal.patient.account.models;

import com.personal.patient.account.entities.Services;
import lombok.Data;

@Data
public class CreatingServiceRequest {
    private Long id;

    private String name;

    private Integer duration;
    public CreatingServiceRequest(Services services){
        this.name = services.getName();
        this.duration = services.getDuration();
        this.id = services.getId();
    }
}
