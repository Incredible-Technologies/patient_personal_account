package com.personal.patient.account.models;

import com.personal.patient.account.entities.ResultCard;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class ListOfResultCard {
    private List<ResultCardData> resultCardList;

    public ListOfResultCard(List<ResultCard> resultCardList){
        this.resultCardList = new ArrayList<>(resultCardList.size());
        for(ResultCard element: resultCardList){
            this.resultCardList.add(new ResultCardData(element));
        }
    }
}
