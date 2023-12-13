package com.personal.patient.account.utils;

import lombok.Data;

import java.time.LocalTime;

@Data
public class Period {
    private LocalTime startTime;

    private LocalTime endTime;

    public Period(LocalTime startTime, LocalTime endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isIncluded(Period period){
        return startTime.isBefore(period.getStartTime()) && endTime.isAfter(period.getEndTime());
    }

    public Period[] leftoverTime(Period period){
        Period previous = new Period(startTime, period.startTime);
        Period after = new Period(period.endTime, endTime);
        return new Period[]{previous, after};
    }
}
