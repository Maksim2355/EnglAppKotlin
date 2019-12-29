package com.example.engapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.*
/*
1. Значение id
2. Login аккаунта
3-4. Пароль и email от аккаунта
5. Список с id работ, которые выложил автор
 */

@Entity
data class DataAccount(
                       val login: String,
                       val email: String,
                       var password: String) {
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
}