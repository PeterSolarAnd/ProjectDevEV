package com.example.call_mapbox_api.data

import com.example.call_mapbox_api.data.local.EvPointDataBase
import com.example.call_mapbox_api.model.EvPointDetails
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val evPointDataBase: EvPointDataBase) : ILocalDataSource {

    override suspend fun fetchPoints(): Flow<List<EvPointDetails>> {
        return evPointDataBase.evPointsDao.getEvPoints()
    }

    override suspend fun updatePoints(evPointsDao: List<EvPointDetails>) {
        evPointDataBase.evPointsDao.insert(evPointsDao)

    }
}

interface ILocalDataSource {

    suspend fun fetchPoints(): Flow<List<EvPointDetails>>

    suspend fun updatePoints(evPointsDao: List<EvPointDetails>)
}

