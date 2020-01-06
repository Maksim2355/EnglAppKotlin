package com.example.engapp.database

import androidx.room.*

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

    //Получение элементов для заголовка
    @Query("SELECT id, title, contentDesc, rating, pathImage FROM DataWorks")
    fun getItem(): List<ItemList?>?

    @Query("SELECT id, title, contentDesc, rating, pathImage FROM DataWorks WHERE id = :id")
    fun getItemById(id: Int): ItemList?

    //Обновление данных
    @Update
    fun update(dataWorks: DataWorks?)

}