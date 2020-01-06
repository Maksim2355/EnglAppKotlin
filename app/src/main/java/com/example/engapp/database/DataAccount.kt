package com.example.engapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

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
                       var password: String,
                       var accountDesc: String?,
                       //Список с id работ пользователя и избранных работ
                       var idWorks: String,
                       var idFavorites: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
