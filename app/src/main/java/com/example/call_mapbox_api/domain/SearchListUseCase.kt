package com.example.call_mapbox_api.domain

import com.example.call_mapbox_api.data.repository.ISearchListRepository
import com.example.call_mapbox_api.model.EvPointDetails
import com.example.call_mapbox_api.data.remote.toEvPointDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchListUseCase @Inject constructor(
    private val searchListRepository: ISearchListRepository, // Flow<List<EvPointsEntity>>
    private val dispatcher: CoroutineDispatcher) : ISearchListUseCase
{

    override suspend fun getEvPointDetails(): Flow<List<EvPointDetails>> =
        withContext(dispatcher) {
            searchListRepository.fetchList().map { items -> items.toEvPointDetails() }
            }
    }

interface ISearchListUseCase {
    suspend fun getEvPointDetails(): Flow<List<EvPointDetails>>
}
