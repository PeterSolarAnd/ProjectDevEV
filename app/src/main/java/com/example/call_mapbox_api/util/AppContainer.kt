package com.example.call_mapbox_api.util

import com.example.call_mapbox_api.api.OpenMapApi
import com.example.call_mapbox_api.domain.SearchListUseCase
import com.example.call_mapbox_api.homescreen.data.EvPointDataSource
import com.example.call_mapbox_api.homescreen.data.SearchListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer{

    fun getRetrofitResult(): Retrofit {
        val mHttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val mOkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(mHttpLoggingInterceptor)
            .build()
        //  https://api.openchargemap.io/
        val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("https://d01abe6f-6cca-4fc5-aaf9-18b2ff7178f4.mock.pstmn.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build()
        }
        return retrofit
    }
    fun getRemoteDataSource(): EvPointDataSource {
        return EvPointDataSource(getRetrofitResult().create(OpenMapApi::class.java))
    }

    fun getDispacher(): CoroutineDispatcher {
        return Dispatchers.Default
    }

    fun getSearchListUseCase(): SearchListUseCase {
        return SearchListUseCase(getRepository(), getDispacher())
    }
    fun getRepository(): SearchListRepository {
        return SearchListRepository(getRemoteDataSource())
    }
}