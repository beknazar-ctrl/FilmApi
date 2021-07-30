package com.example.filmapi.ui;

import android.app.Application;

import androidx.room.Room;

import com.example.filmapi.frameworks.AppDataBase;

public class App  extends Application {

    private static AppDataBase appDataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        appDataBase= Room
                .databaseBuilder(this,AppDataBase.class,"database")
                .allowMainThreadQueries()
                .build();
    }

    public static AppDataBase getAppDataBase() {
        return appDataBase;
    }
}
