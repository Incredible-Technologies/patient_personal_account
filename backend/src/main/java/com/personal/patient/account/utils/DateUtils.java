package com.personal.patient.account.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
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
}
