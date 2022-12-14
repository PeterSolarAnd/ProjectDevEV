package com.example.call_mapbox_api.data.local

import androidx.room.TypeConverter
import com.example.call_mapbox_api.data.remote.Connection
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.xml.transform.Source

object Converters {
    @TypeConverter
    fun fromString(value: String?): List<Connection> {
        val listType: Type = object : TypeToken<List<Connection?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<Connection?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}

   /* @TypeConverter
    fun fromSource(source: Source): String{
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source{
        return Source(name, name)
    }*/

/*
class ArrayListConverter {

    @TypeConverter
    fun fromStringArrayList(value: ArrayList<String>): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringArrayList(value: String): ArrayList<String> {
        return try {
            Gson().fromJson<ArrayList<String>>(value) //using extension function
        } catch (e: Exception) {
            arrayListOf()
        }
    }
}*/

/*data class UserEntity(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "address_lines") var addressLines: ArrayList<String>,
    @PrimaryKey val id: Int = 0
)*/
