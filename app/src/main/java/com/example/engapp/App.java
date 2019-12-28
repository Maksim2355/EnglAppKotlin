package com.example.engapp;

import android.app.Application;

import androidx.room.Room;

import com.example.engapp.database.AppDatabase;

public class App extends Application {

    public static App instance;



    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }

    public static App getInstance() {
        return instance;
    }


}