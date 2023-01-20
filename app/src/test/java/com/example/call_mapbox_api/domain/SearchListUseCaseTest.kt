package com.example.call_mapbox_api.domain

import com.example.call_mapbox_api.data.remote.AddressInfo
import com.example.call_mapbox_api.data.remote.Connections
import com.example.call_mapbox_api.data.remote.EvPointsEntity
import com.example.call_mapbox_api.data.repository.ISearchListRepository
import com.example.call_mapbox_api.fakeData.fakeEvPointsEntity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchListUseCaseTest {

    private lateinit var searchListUseCase: SearchListUseCase

    @Mock
    private lateinit var searchListRepository: ISearchListRepository

    @Before
    fun setUp() {
        searchListUseCase = SearchListUseCase(searchListRepository, Dispatchers.Default)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Convert EvPointEntity to EvPointDetails and check one item`() {
        runTest {
            `when`(searchListRepository.fetchList()).thenReturn(fakeEvPointsEntity())
            val actual = searchListUseCase.getEvPointDetails().toList()[0]
                .toList().map { it.NumberOfPoints }
            val expected = fakeEvPointsEntity().toList()[0].map { it.NumberOfPoints }
            assertEquals(expected, actual)
        }
    }
}
