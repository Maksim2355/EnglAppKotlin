package com.example.engapp

import android.app.Application
import androidx.room.Room
import com.example.engapp.database.AppDatabase
import com.example.engapp.database.UserData
import com.example.engapp.database.UserDataDao


class App : Application() {
    var database: AppDatabase? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "Db"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

        val userDao = database!!.userDao()
        if (userDao?.getUserData() == null){
            //Если нет ячейки активного пользователя, то добавляем
            val userActive = UserData()
            userDao!!.insertAll(userActive)
        }
    }

    companion object {
        var instance: App? = null
    }


}