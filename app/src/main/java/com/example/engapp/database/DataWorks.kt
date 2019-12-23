package com.example.engapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
Различные параметры data класса
1. id данного элемента
2. title заголовок в элементе списка
3. contentDesc краткое описание элемента
4-5. ContentRu и ContentEn содержание текста на русском и английском
6. Рейтинг работы
7. Путь к картинке для обложки работы
9. Путь к аудиофайлу для обложки работы
10. Флаг для поиска по разделам
    Значение 0- Элемент находится в разделе allWorks
    Значение 1- Элемент находится в разделе Favorite
    Значение 2- Элемент находится в разделе Profile
 */
@Entity
data class DataWorks(@PrimaryKey(autoGenerate = true) val id: Int, var title: String,
                        var contentDesc: String, var contentRu: String,
                        var contentEn: String, var rating: String,
                        var pathImage: String, var pathAudio: String,
                     var FLAG_SECTION: Int) {
}