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
10.id автора работы
 */
@Entity
class DataWorks {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var title: String? = null
    var contentDesc: String? = null
    var contentRu: String? = null
    var contentEn: String? = null
    var rating: Int? = null
    var pathImage: String? = null
    var pathAudio: String? = null
    var idAuthor: Int? = null
}