package com.example.engapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class UserData {
    @PrimaryKey
    var id: Int? = null
    var userId: Int? = null
}