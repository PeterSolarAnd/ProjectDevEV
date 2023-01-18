package com.example.call_mapbox_api.data.repository

import com.example.call_mapbox_api.data.IEvPointLocalDataSource
import com.example.call_mapbox_api.data.IEvPointRemoteDataSource
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class SearchListRepositoryMockTest {

    @InjectMocks
    private lateinit var searchListRepository: SearchListRepository

    @Mock
    lateinit var evPointRemoteDataSource: IEvPointRemoteDataSource

    @Mock
    lateinit var evPointLocalDataSource: IEvPointLocalDataSource

    @Test
    fun `Check local and remote database items in Flow`() {

        runBlocking {
            val firstItem = searchListRepository.fetchList()
            assertEquals(evPointRemoteDataSource.getLatestEvPoint(), firstItem)
            assertEquals(evPointLocalDataSource.fetchPoints(), firstItem)
        }
    }
}