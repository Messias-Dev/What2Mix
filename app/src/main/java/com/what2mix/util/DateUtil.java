package com.what2mix.util;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static String getDate() {

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        DateFormat formatter = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String createdAt = formatter.format(calendar.getTime());
         return createdAt;
    }

}
