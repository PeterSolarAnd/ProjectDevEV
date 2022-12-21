package com.example.call_mapbox_api.data.repository

import android.util.Log
import com.example.call_mapbox_api.data.IEvPointDataSource
import com.example.call_mapbox_api.data.ILocalDataSource
import com.example.call_mapbox_api.data.remote.EvPointsBrakeItem
import kotlinx.coroutines.flow.Flow

class SearchListRepository(
    private val evPointDataSource: IEvPointDataSource,
    private val localDataSource: ILocalDataSource
) : ISearchListRepository {

    override suspend fun fetchList(): Flow<List<EvPointsBrakeItem>> {
        try {
            evPointDataSource.getLatestEvPoint().collect { items ->
                localDataSource.updatePoints(items)
            }
        } catch (e: Exception) {
            Log.d("SearchListRepository", "Connection failed using local data base")
        }
        return localDataSource.fetchPoints()
    }
}

interface ISearchListRepository {
    suspend fun fetchList(): Flow<List<EvPointsBrakeItem>>
}

