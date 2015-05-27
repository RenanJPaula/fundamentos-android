package com.example.administrador.myapplication;

import android.app.Application;

import com.example.administrador.myapplication.util.AppUtil;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppUtil.CONTEXT = getApplicationContext();
    }
}
