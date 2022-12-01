package com.example.call_mapbox_api.homescreen.data

import com.example.call_mapbox_api.remote.EvPointsBrakeItemX
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.Flow

class SearchListRepository(
    private val evPointDataSource: IEvPointDataSource,
    //private val localData: LocalData
) : ISearchListRepository {

    override suspend fun fetchList(): Flow<List<EvPointsBrakeItemX>> =
        evPointDataSource.getLatestEvPoint()
}

interface ISearchListRepository {
    suspend fun fetchList(): Flow<List<EvPointsBrakeItemX>>
}

