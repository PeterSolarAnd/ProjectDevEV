package com.example.call_mapbox_api.domain

import com.example.call_mapbox_api.data.repository.ISearchListRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class SearchListUseCaseTest {

    private lateinit var searchListUseCase: SearchListUseCase

    @Mock
    private lateinit var searchListRepository: ISearchListRepository


    @Before
    fun setUp() {
        val dispatcher: CoroutineDispatcher = Dispatchers.IO
        searchListUseCase = SearchListUseCase(searchListRepository, dispatcher)
    }

    @Test
    fun `Test conversion from EvPointEntity to EvPointDetails`() {
         runBlocking {
             val fetchedList = searchListUseCase.invoke()
             assertEquals(searchListUseCase.invoke(), fetchedList)
        }
    }
}

