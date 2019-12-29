package com.example.engapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DataWorksDao {
    // Добавление Данных в бд
    @Insert
    fun insertAll(item: DataWorks?)

    //Извлеченеи данных из БД
    @Delete
    fun deleteWorks(item: DataWorks?)

    //Получение всех элементов
    @Query("SELECT * FROM DataWorks")
    fun getAllWorks(): List<DataWorks?>?

    //Получение элемента по его id
    @Query("SELECT * FROM DataWorks WHERE id = :id")
    fun getById(id: Int): DataWorks?

    //Получение элементов по разделам
    @Query("SELECT * FROM DataWorks WHERE FLAG_SECTION = :FLAG_SECTION")
    fun getSectionWorks(FLAG_SECTION: Int): List<DataWorks>?

    //Получение элементов для заголовка
    @Query("SELECT id, title, contentDesc, rating, pathImage FROM DataWorks")
    fun getItem(): List<ItemList?>?
}