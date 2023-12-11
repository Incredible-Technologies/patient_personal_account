package com.personal.patient.account.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm");
        Date date = new Date();
        try {
            date = formatter.parse(stringTime);
        } catch (ParseException e) {
            // Ошибка парсинга даты
            e.printStackTrace();
        }
        return date;
    }

    public Date addMinutes(Date date, Integer minutes){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }
}
