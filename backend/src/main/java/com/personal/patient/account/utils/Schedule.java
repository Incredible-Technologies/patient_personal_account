package com.personal.patient.account.utils;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private final List<Period> periods;

    public Schedule(Period period){
        periods = new ArrayList<>();
        periods.add(period);
    }

    public boolean addEmploymentPeriod(Period period){
        for(int i = 0; i < periods.size(); ++i){
            if(periods.get(i).isIncluded(period)){
                Period[] newPeriods = periods.get(i).leftoverTime(period);
                Period preview = newPeriods[0];
                Period after = newPeriods[1];
                periods.remove(i);
                periods.add(i, preview);
                periods.add(i+1, after);
                return true;
            }
        }
        return false;
    }
}
