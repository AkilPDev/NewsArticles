package com.article.newsapp

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromString(value: String?): List<String>? {
        return value?.let {
            val type = object : TypeToken<List<String>>() {}.type
            Gson().fromJson(value, type)
        }
    }

    @TypeConverter
    fun toString(list: List<String>?): String? {
        return Gson().toJson(list)
    }
}