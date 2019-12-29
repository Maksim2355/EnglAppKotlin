package com.example.engapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class UserData {
    @PrimaryKey
    val id: Int = 0
    var userId: Int? = null
}