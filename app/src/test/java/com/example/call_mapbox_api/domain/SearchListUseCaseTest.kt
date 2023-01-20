package com.example.call_mapbox_api.domain

import com.example.call_mapbox_api.data.remote.AddressInfo
import com.example.call_mapbox_api.data.remote.Connections
import com.example.call_mapbox_api.data.remote.EvPointsEntity
import com.example.call_mapbox_api.data.repository.ISearchListRepository
import com.example.call_mapbox_api.model.toEvPointsBreakItems
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

    private fun fakeEvPointsEntity(): Flow<List<EvPointsEntity>> {

        val fakeAddressInfo = AddressInfo(
            "add1",
            "add2",
            4,
            5,
            3.4,
            4,
            5.6,
            3.2,
            "postcode",
            "state",
            "title",
            "town"
        )

        val fakeConnetion = Connections(
            3,
            4,
            7,
            7,
            9,
            0,
            1.4,
            8,
            8
        )
        val fakeEvPointsEntity = EvPointsEntity(
            4,
            "Datalast",
            5,
            8,
            "datacreated",
            "date",
            5,
            true,
            3,
            5,
            5,
            "uuid",
            "usagecostmoney",
            5,
            listOf(fakeConnetion),
            fakeAddressInfo
        )
        return flow {
            emit(listOf(fakeEvPointsEntity))
        }
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Convert EvPointEntity to EvPointDetails and check one item`() {
        runTest {
            `when`(searchListRepository.fetchList()).thenReturn(fakeEvPointsEntity())
            val actual = searchListUseCase.getEvPointDetails().toList()[0]
                .toEvPointsBreakItems().toList().map { it.NumberOfPoints }
            val expected = fakeEvPointsEntity().toList()[0].map { it.NumberOfPoints }
            assertEquals(expected, actual)
        }
    }
}
