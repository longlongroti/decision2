package com.shanxi.coal.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class MyDateTimeUtils {
    private MyDateTimeUtils() {
    }

    public static String strNow(String pattern) {
        SimpleDateFormat fmt = new SimpleDateFormat(pattern);
        return fmt.format(new Date());
    }

    public static Integer getYear(int num) {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE, num);
        return date.get(Calendar.YEAR);
    }

    public static Integer getMonth(int num) {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE, num);
        return date.get(Calendar.MONTH) + 1;
    }

    public static Integer getDate(int num) {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE, num);
        return date.get(Calendar.DATE);
    }

    public static Date string2Date(String val, String reg) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(reg);
        Date d = sdf.parse(val);
        return d;
    }

    public static String pastDays(int days) {
        return getString(days, Calendar.DATE, "yyyy-MM-dd");
    }

    public static String pastMonth(int month) {
        return getString(month, Calendar.MONTH, "yyyy-MM");
    }

    public static String getString(int days, int type, String regx) {
        SimpleDateFormat format = new SimpleDateFormat(regx);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(type, -days);
        Date m = c.getTime();
        String mon = format.format(m);
        return mon;
    }
}
