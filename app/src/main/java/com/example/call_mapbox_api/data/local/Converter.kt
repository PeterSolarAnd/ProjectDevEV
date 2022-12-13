package com.example.call_mapbox_api.data.local

import androidx.room.TypeConverter
import javax.xml.transform.Source

class Converter {

   /* @TypeConverter
    fun fromSource(source: Source): String{
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source{
        return Source(name, name)
    }*/
}

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
