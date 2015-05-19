package com.example.administrador.myapplication.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class AppUtil {

    public static final Locale LOCALE_PT_BR = new Locale("pt", "BR");

    public static final String PATTERN_DATE = "dd/MM/yyyy";
    public static final String PATTERN_TIME = "HH:mm:ss";
    public static final String PATTERN_DATETIME = "dd/MM/yyyy HH:mm:ss";
    public static final String PATTERN_NUMBER = "#.00";

    private AppUtil() {
        super();
    }

    public static <T> T get(Object element) {
        return (T) element;
    }

    public static String format(Number number, String pattern) {
        final DecimalFormat decimalFormat = AppUtil.get(NumberFormat.getNumberInstance(Locale.US));
        decimalFormat.applyPattern(pattern);
        return decimalFormat.format(number);
    }

    public static String format(Date date, String pattern) {
        final DateFormat dateTimeFormat = new SimpleDateFormat(pattern, AppUtil.LOCALE_PT_BR);
        return dateTimeFormat.format(date);
    }
}
