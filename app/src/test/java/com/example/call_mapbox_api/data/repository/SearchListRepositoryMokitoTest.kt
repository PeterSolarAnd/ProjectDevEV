package com.example.call_mapbox_api.data.repository

import com.example.call_mapbox_api.data.IEvPointLocalDataSource
import com.example.call_mapbox_api.data.IEvPointRemoteDataSource
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock


@RunWith(MockitoJUnitRunner::class)
class SearchListRepositoryMokitoTest {


    private lateinit var searchListRepository: SearchListRepository

    @Mock
    lateinit var evPointRemoteDataSource: IEvPointRemoteDataSource

    @Mock
    lateinit var evPointLocalDataSource: IEvPointLocalDataSource

    @Before
    fun setUp() {
        searchListRepository = SearchListRepository(evPointRemoteDataSource, evPointLocalDataSource)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `Check local and remote database items in Flow`() {
        runBlocking {
            val evPointRemoteDataSource = mock<IEvPointRemoteDataSource>{
                evPointRemoteDataSource.getLatestEvPoint()
            }
            val evPointLocalDataSource = mock<IEvPointLocalDataSource>{
                evPointLocalDataSource.fetchPoints()
            }
            val firstItem = searchListRepository.fetchList()
            assertEquals(evPointRemoteDataSource.getLatestEvPoint(), firstItem)
            assertEquals(evPointLocalDataSource.fetchPoints(), firstItem)
        }
    }
}