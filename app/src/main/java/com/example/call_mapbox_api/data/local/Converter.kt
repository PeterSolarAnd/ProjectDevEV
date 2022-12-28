package com.example.call_mapbox_api.data.local

import androidx.room.TypeConverter
import com.example.call_mapbox_api.data.remote.AddressInfo
import com.example.call_mapbox_api.data.remote.Connections
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object ConnectionConverter {
    @TypeConverter
    fun fromString(value: String?): List<Connections> {
        val listType: Type = object : TypeToken<List<Connections?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<Connections?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}

object AddressConverter {

    @TypeConverter
    fun fromString(value: String?): AddressInfo {
        val listType: Type = object : TypeToken<AddressInfo?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: AddressInfo?): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    /*@TypeConverter
    fun fromAddress(addressInfo: AddressInfo): AddressInfo {
        return AddressInfo(
            addressInfo.AddressLine2,
            addressInfo.AddressLine1,
            addressInfo.Postcode?.toInt(),
            addressInfo.Title?.toInt(),
            addressInfo.StateOrProvince?.toDouble(),
            addressInfo.Town?.toInt(),
            addressInfo.CountryID?.toDouble(),
            addressInfo.DistanceUnit?.toDouble(),
            addressInfo.ID?.toString(),
            addressInfo.Latitude?.toString(),
            addressInfo.Longitude?.toString(),
            addressInfo.Distance?.toString()
        )
    }

    @TypeConverter
    fun toAddress(addressInfo: String): AddressInfo {
        return AddressInfo(
            addressInfo,
            addressInfo,
            addressInfo.toInt(),
            addressInfo.toInt(),
            addressInfo.toDouble(),
            addressInfo.toInt(),
            addressInfo.toDouble(),
            addressInfo.toDouble(),
            addressInfo,
            addressInfo,
            addressInfo,
            addressInfo
        )

    }*/
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
