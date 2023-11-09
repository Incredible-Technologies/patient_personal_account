package com.personal.patient.account.models;


import lombok.Data;

@Data
public class DownloadResultFile {
    private Long cardId;
    public DownloadResultFile(Long cardId){
        this.cardId = cardId;
    }
}
