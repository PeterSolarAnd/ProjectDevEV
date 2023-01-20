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
            AddressLine1 = "add1",
            AddressLine2 = "add2",
            CountryID = 4, 
            DistanceUnit = 5,
            Distance = 3.4,
            ID = 4,
            Latitude = 5.6,
            Longitude = 3.2,
            Postcode = "postcode",
            StateOrProvince = "state",
            Title = "title",
            Town = "town"
        )

        val fakeConnetion = Connections(
            ConnectionTypeID = 3,
            CurrentTypeID = 4,
            ID = 7,
            Voltage = 7,
            Amps = 9,
            LevelID = 0,
            PowerKW = 1.4,
            Quantity = 8,
            StatusTypeID = 8
        )
        val fakeEvPointsEntity = EvPointsEntity(
            ID =4,
            DateLastVerified = "Datalast",
            DataProviderID = 5,
            DataQualityLevel = 8,
            DateCreated = "datacreated",
            DateLastStatusUpdate = "date",
            NumberOfPoints = 5,
            IsRecentlyVerified = true,
            OperatorID = 3,
            StatusTypeID = 5,
            SubmissionStatusTypeID = 5,
            UUID = "uuid",
            UsageCost = "usagecostmoney",
            UsageTypeID = 5,
            Connections = listOf(fakeConnetion),
            AddressInfo = fakeAddressInfo
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
