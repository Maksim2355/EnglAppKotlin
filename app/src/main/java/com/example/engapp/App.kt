package com.example.engapp

import android.app.Application
import androidx.room.Room
import com.example.engapp.database.AppDatabase


class App : Application() {
    var database: AppDatabase? = null
        private set
    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .build()
    }

    companion object {
        var instance: App? = null
    }
}