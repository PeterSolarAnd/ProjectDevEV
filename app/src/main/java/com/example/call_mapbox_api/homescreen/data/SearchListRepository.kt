package com.example.call_mapbox_api.homescreen.data

import androidx.lifecycle.MutableLiveData
import com.example.call_mapbox_api.homescreen.data.EvPointDataSource
import com.example.call_mapbox_api.model.EvPointDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SearchListRepository(
    private val evPointDataSource: EvPointDataSource,
    //private val localData: LocalData

) {
    fun fetchList(): Flow<List<EvPointDetails>> =
        evPointDataSource.getLatestEvPoint().map { list -> list }
}

