package com.example.call_mapbox_api.data.repository

import com.example.call_mapbox_api.data.IEvPointLocalDataSource
import com.example.call_mapbox_api.data.IEvPointRemoteDataSource
import com.example.call_mapbox_api.fakeData.fakeEvPointsEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@RunWith(MockitoJUnitRunner::class)
class SearchListRepositoryTest {

    @InjectMocks
    private lateinit var searchListRepository: SearchListRepository

    @Mock
    lateinit var evPointRemoteDataSource: IEvPointRemoteDataSource

    @Mock
    lateinit var evPointLocalDataSource: IEvPointLocalDataSource

    @Test
    fun `Check local and remote data source objects`() {

        runBlocking {
            val actual = searchListRepository.fetchList()
            assertEquals(evPointRemoteDataSource.getLatestEvPoint(), actual)
            assertEquals(evPointLocalDataSource.fetchPoints(), actual)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Test localDataSource entities and verify one item`() {
        runTest {
            `when`(evPointLocalDataSource.fetchPoints()).thenReturn(fakeEvPointsEntity())
            val actual = searchListRepository.fetchList().toList()[0]
                .toList().map { it.NumberOfPoints }
            val expected = fakeEvPointsEntity().toList()[0].map { it.NumberOfPoints }
            assertEquals(expected, actual)
        }
    }
}