package com.example.call_mapbox_api.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.call_mapbox_api.MyApplication
import com.example.call_mapbox_api.model.EvPointDetails
import kotlinx.coroutines.CoroutineScope


@Database(entities = [EvPointDetails::class], version = 1, exportSchema = false)
abstract class EvPointDataBase : RoomDatabase() {

    abstract val evPointsDao: EvPointsDao

    companion object {
        @Volatile
        private var INSTANCE: EvPointDataBase? = null

        fun getDatabase(context: MyApplication, applicationScope: CoroutineScope): EvPointDataBase {
            // if the INSTANCE is not null, then return it,
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