package com.example.call_mapbox_api.homescreen.data

import com.example.call_mapbox_api.api.OpenMapApi
import com.example.call_mapbox_api.model.EvPointDetails
import com.example.call_mapbox_api.util.EvApiParser
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EvPointDataSource(
    private val openMapApi: OpenMapApi,
    private val refreshIntervalMs: Long = 5000
) {
    fun getLatestEvPoint(): Flow<List<EvPointDetails>> {
        return flow {
            while (true){
            val result = openMapApi.getMaxResults()
            val latestEvPoint = EvApiParser(result)
            emit(latestEvPoint) // Emits the result of the request to the flow
            delay(refreshIntervalMs) // Suspends the coroutine for some time
        }
    }

    }
}
