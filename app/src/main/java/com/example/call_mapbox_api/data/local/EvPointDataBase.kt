package com.example.call_mapbox_api.data.local

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.call_mapbox_api.MyApplication
import com.example.call_mapbox_api.data.remote.EvPointsBrakeItem
import com.example.call_mapbox_api.model.EvPointDetails
import kotlinx.coroutines.CoroutineScope


@Database(entities = [EvPointsBrakeItem::class], version = 2, exportSchema = false)
@TypeConverters(ConnectionConverter::class, AddressConverter::class)
abstract class EvPointDataBase : RoomDatabase() {

    abstract val evPointsDao: EvPointsDao

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: EvPointDataBase? = null

        fun getInstance(context: Context): EvPointDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }
        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): EvPointDataBase {
            return Room.databaseBuilder(context, EvPointDataBase::class.java, "evPointsDb")
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // moving to a new thread
                            getInstance(context)
                        }
                    })
                .build()
        }
    }
}
   /* companion object {
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
}*/