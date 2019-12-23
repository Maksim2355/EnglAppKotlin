package com.example.engapp.database

import androidx.room.*


@Dao
interface ActiveUserDataDao {
    @Update
    fun updateUser(item: ActiveUserData?)
    
    //Получение всех элемента
    @Query("SELECT * FROM ActiveUserData")
    fun getUser(): ActiveUserData?


}