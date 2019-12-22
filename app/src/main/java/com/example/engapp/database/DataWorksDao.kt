package com.example.engapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.engapp.database.DataWorks

@Dao
interface DataWorksDao {
    // Добавление Person в бд
    @Insert
    fun insertAll(vararg item: DataWorks?)


    @Delete
    fun deleteWorks(item: DataWorks?)


    @Query("SELECT * FROM DataWorks")
    fun getAllWorks(): List<DataWorks?>?
}