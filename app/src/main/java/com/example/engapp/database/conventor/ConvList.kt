package com.example.engapp.database.conventor

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.util.*
import java.util.stream.Collectors


class ConvList {
    @RequiresApi(Build.VERSION_CODES.N)
    @TypeConverter
    fun fromString(data: MutableList<String?>): String {
        return data.stream().collect(Collectors.joining(","))
    }

    @TypeConverter
    fun toList(data: String): MutableList<Array<String>> {
        return mutableListOf(data.split(",").toTypedArray())
    }
}