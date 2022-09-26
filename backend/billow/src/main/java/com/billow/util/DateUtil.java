package com.billow.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String toYYYY_MM_DD(Date date) {
        if (date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
            return formatter.format(date);
        } else {
            return null;
        }
    }

    public static String toHH_mm(Date date) {
        if (date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            return formatter.format(date);
        } else {
            return null;
        }
    }

    public static Date toDate(String date) throws ParseException {
        if (!date.equals("")) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm");
            return formatter.parse(date);
        } else {
            return null;
        }
    }

    public static String toAlarmDate(Date broadcastingTime) {
        if (broadcastingTime != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
            return formatter.format(broadcastingTime);
        } else {
            return null;
        }
    }

    public static String toAlarmString(Date broadcastingTime) {
        if (broadcastingTime != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
            return formatter.format(broadcastingTime);
        } else {
            return null;
        }
    }
}
