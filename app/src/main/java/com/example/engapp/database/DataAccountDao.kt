package com.example.engapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DataAccountDao {
    @Insert
    fun insertAccount(item: DataAccount?)

    @Delete
    fun deleteAccount(item: DataAccount?)

    //Получение всех элементов
    @Query("SELECT * FROM DataAccount")
    fun getAllAccount(): List<DataAccount?>?

    //Получение лишь некоторых данных элемента
    @Query("SELECT login, password, id FROM DataAccount")
    fun getLoginInfo(): List<AccountInData>?

    //Получение элемента по его id
    @Query("SELECT * FROM DataAccount WHERE id = :id")
    fun getById(id: Int): DataAccount?


}