package com.example.call_mapbox_api.homescreen.data

import com.example.call_mapbox_api.api.OpenMapApi
import com.example.call_mapbox_api.remote.EvPointsBrakeItemX
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EvPointDataSource(
    private val openMapApi: OpenMapApi,
    //private val refreshIntervalMs: Long = 5000
) : IEvPointDataSource {
    override suspend fun getLatestEvPoint(): Flow<List<EvPointsBrakeItemX>> {
        return flow {
            val result = openMapApi.getMaxResults()
            emit(result) // Emits the result of the request to the flow
        }
    }
}

interface IEvPointDataSource {
    suspend fun getLatestEvPoint(): Flow<List<EvPointsBrakeItemX>>
}

