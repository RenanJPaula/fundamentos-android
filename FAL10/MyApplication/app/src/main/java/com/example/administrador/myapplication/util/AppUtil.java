package com.example.administrador.myapplication.util;

import android.content.Context;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class AppUtil {

    public static final Locale LOCALE_PT_BR = new Locale("pt", "BR");

    private static final String PATTERN_DATE = "dd/MM/yyyy";
    private static final String PATTERN_TIME = "HH:mm:ss";
    private static final String PATTERN_DATETIME = "dd/MM/yyyy HH:mm:ss";
    private static final String PATTERN_NUMBER = "#.00";
    public static Context CONTEXT;

    private AppUtil() {
        super();
    }

    public static <T> T get(Object element) {
        return (T) element;
    }

    public static String formatDate(Date date) {
        return format(date, AppUtil.PATTERN_DATE);
    }

    public static String formatDateTime(Date date) {
        return format(date, AppUtil.PATTERN_DATETIME);
    }

    public static String formatTime(Date date) {
        return format(date, AppUtil.PATTERN_TIME);
    }

    public static String formatDecimal(Number number) {
        final DecimalFormat decimalFormat = AppUtil.get(NumberFormat.getNumberInstance(Locale.US));
        decimalFormat.applyPattern(AppUtil.PATTERN_NUMBER);
        return decimalFormat.format(number);
    }

    private static String format(Date date, String pattern) {
        final DateFormat dateTimeFormat = new SimpleDateFormat(pattern, AppUtil.LOCALE_PT_BR);
        return dateTimeFormat.format(date);
    }
}
