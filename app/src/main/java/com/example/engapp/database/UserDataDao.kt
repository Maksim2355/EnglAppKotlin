package com.example.engapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDataDao {

    //Добавление
    @Insert
    fun insertAll(item: UserData?)

    //Получение всех элементов
    @Query("SELECT * FROM UserData")
    fun getUserData(): UserData?

    @Update
    fun update(userData: UserData?)

}