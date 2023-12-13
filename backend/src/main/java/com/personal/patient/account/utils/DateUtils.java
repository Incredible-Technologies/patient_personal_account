package com.personal.patient.account.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

@Component
@Data
@NoArgsConstructor
public class DateUtils {
    public Date parseStringToDate(String stringDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Date date = new Date();
        try {
            date = dateFormat.parse(stringDate);
        } catch (ParseException e) {
            // Ошибка парсинга даты
            e.printStackTrace();
        }
        return date;
    }

    public Date parseStringToTime(String stringTime){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        try {
            date = formatter.parse(stringTime);
        } catch (ParseException e) {
            // Ошибка парсинга даты
            e.printStackTrace();
        }
        return date;
    }
  
    public String parseDateToString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public Date addMinutes(Date date, Integer minutes){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    public LocalTime dateToTime(Date date){
        Time time = new Time(date.getTime());
        return time.toLocalTime();
    }
}
