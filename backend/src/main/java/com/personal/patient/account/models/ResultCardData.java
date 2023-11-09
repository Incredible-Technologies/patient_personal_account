package com.personal.patient.account.models;

import com.personal.patient.account.entities.ResultCard;
import lombok.Data;

import java.util.Date;

@Data
public class ResultCardData {
    private Long id;
    private String name;
    private String description;
    private Date dateOfMake;
    private Date dateOfShouldReady;
    private Date dateOfDelivered;
    private String hospitalAddress;
    private Long resultFileId;
    private Long userId;

    public ResultCardData(ResultCard resultCard){
        this.id = resultCard.getId();
        this.name = resultCard.getName();
        this.description = resultCard.getDescription();
        this.dateOfMake = resultCard.getDateOfMake();
        this.dateOfShouldReady = resultCard.getDateOfShouldReady();
        this.dateOfDelivered = resultCard.getDateOfDelivered();
        this.hospitalAddress = resultCard.getHospitalAddress();
        this.resultFileId = null;
        if(resultCard.getResultFile()!=null)
            this.resultFileId =  resultCard.getResultFile().getId();
        this.userId = resultCard.getUser().getId();
    }
}
