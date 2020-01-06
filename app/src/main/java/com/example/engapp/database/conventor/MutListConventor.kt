package com.example.engapp.database.conventor

class MutListConventor {
    fun fromMutable(list: MutableList<Int?>?): String? {
        return list.toString()
    }

    fun toMutable(data: String?): MutableList<Int> {
        return data as MutableList<Int>
    }

}