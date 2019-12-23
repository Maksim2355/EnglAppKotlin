package com.example.engapp.database

import androidx.room.PrimaryKey
/*
1. Id элемента
2. Флаг, который показывает, есть ли авторизация в приложении
3. Id профиля, в который выполнен вход
 */

data class ActiveUserData(@PrimaryKey(autoGenerate = true)val id: String,
                          var SIGN_IN: Int, var idAccount: Int) {
}