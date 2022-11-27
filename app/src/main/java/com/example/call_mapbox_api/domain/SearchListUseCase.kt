package com.example.call_mapbox_api.domain

import androidx.lifecycle.MutableLiveData
import com.example.call_mapbox_api.homescreen.data.SearchListRepository
import com.example.call_mapbox_api.model.EvPointDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchListUseCase (
    private val searchListRepository: SearchListRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default)
    {
        var listOfItems = MutableLiveData<List<EvPointDetails>>()

        suspend operator fun invoke(): MutableLiveData<List<EvPointDetails>> {
            withContext(dispatcher) {
                searchListRepository.fetchList().collect { items ->
                    listOfItems.postValue(items)
                }
            }
            return listOfItems
        }
    }
