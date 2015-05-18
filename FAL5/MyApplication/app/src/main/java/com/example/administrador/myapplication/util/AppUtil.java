package com.example.administrador.myapplication.util;

import java.util.Locale;

public class AppUtil {

    public static final Locale LOCALE_PT_BR = new Locale("pt", "BR");

    public static <T> T get(Object element) {
        return (T) element;
    }
}
