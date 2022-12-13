package com.example.call_mapbox_api.data

import com.example.call_mapbox_api.data.local.EvPointDataBase
import com.example.call_mapbox_api.data.local.EvPointsDao
import com.example.call_mapbox_api.data.remote.EvPointsBrakeItem
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val evPointDataBase: EvPointDataBase) : ILocalDataSource {

    override suspend fun fetchPoints(): Flow<List<EvPointsBrakeItem>> {
        return evPointDataBase.evPointsDao.getEvPoints()
    }

    override suspend fun updatePoints(evPointsDao: List<EvPointsBrakeItem>) {
        evPointDataBase.evPointsDao.insert(evPointsDao)

    }
}

interface ILocalDataSource {

    suspend fun fetchPoints(): Flow<List<EvPointsBrakeItem>>

    suspend fun updatePoints(evPointsDao: List<EvPointsBrakeItem>)
}

