package com.example.engapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataWorks(@PrimaryKey val id: Int, var title: String,
                        var contentDesc: String, var contentRu: String,
                        var contentEn: String, var rating: String,
                        var pathImage: String, var pathAudio: String) {
}