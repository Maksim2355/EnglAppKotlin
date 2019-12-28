package com.example.engapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DataAccount::class, DataWorks::class, UserData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun worksDao(): DataWorksDao?
    abstract fun accountDao(): DataAccountDao?
    abstract fun userDao(): UserDataDao?
}