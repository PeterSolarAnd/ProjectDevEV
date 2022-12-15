package com.example.call_mapbox_api.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.call_mapbox_api.data.remote.EvPointsBrakeItem
import kotlinx.coroutines.flow.Flow

@Dao
interface EvPointsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg evPointsEntity: List<EvPointsBrakeItem>)

    @Query("SELECT * FROM evPointsDb ORDER BY ID")
    fun getEvPoints(): Flow<List<EvPointsBrakeItem>>

    @Query("DELETE FROM evPointsDb")
    suspend fun deleteAll()


}