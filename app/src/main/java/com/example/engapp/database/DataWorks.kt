package com.example.engapp.database

import android.os.Parcel
import android.os.Parcelable
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
class DataWorks() : Parcelable {
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

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        title = parcel.readString()
        contentDesc = parcel.readString()
        contentRu = parcel.readString()
        contentEn = parcel.readString()
        rating = parcel.readValue(Int::class.java.classLoader) as? Int
        pathImage = parcel.readString()
        pathAudio = parcel.readString()
        idAuthor = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataWorks> {
        override fun createFromParcel(parcel: Parcel): DataWorks {
            return DataWorks(parcel)
        }

        override fun newArray(size: Int): Array<DataWorks?> {
            return arrayOfNulls(size)
        }
    }
}