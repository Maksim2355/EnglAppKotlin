package com.example.engapp

import java.util.*


class ListId(var data: String) {
    private var listMu = mutableListOf<Int>()
    init {
        //Убираем [ ]
        if (!((data == "[]") || (data == "")))
        {
            data = data.substringAfter('[')
            data = data.substringBeforeLast(']')
            while (true) {
                if (',' in data) {
                    val elem = data.substringBefore(',')
                    listMu.add(elem.toInt())
                    data = data.substringAfter(',').trim(' ')
                } else {
                    listMu.add(data.trim(' ').toInt())
                    break
                }
            }
        }
    }
    fun delItem(item: Int) {
        listMu.remove(item)
    }

    fun addItem(item: Int) {
        listMu.add(item)
    }
    fun getList(): List<Int> {
        return listMu.toList()
    }
    fun elemInList(item: Int) : Boolean {
        return listMu.contains(item)
    }
}