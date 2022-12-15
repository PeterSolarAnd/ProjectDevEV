package com.example.call_mapbox_api.data.local

import android.content.Context
import androidx.room.*
import com.example.call_mapbox_api.MyApplication
import com.example.call_mapbox_api.model.EvPointDetails
import kotlinx.coroutines.CoroutineScope


@Database(entities = [EvPointDetails::class], version = 1, exportSchema = false)
@TypeConverters(ConnectionConverter::class, AddressConverter::class)
abstract class EvPointDataBase : RoomDatabase() {

    abstract val evPointsDao: EvPointsDao

    companion object {
        @Volatile
        private var INSTANCE: EvPointDataBase? = null

        fun getDatabase(context: Context): EvPointDataBase {
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    EvPointDataBase::class.java,
                    "evPointsDb"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}