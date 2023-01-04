/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.call_mapbox_api.di

import android.content.Context
import androidx.room.Room
import com.example.call_mapbox_api.data.OpenMapApi
import com.example.call_mapbox_api.data.local.EvPointDataBase
import com.example.call_mapbox_api.data.local.EvPointsDao
import com.example.call_mapbox_api.data.repository.ISearchListRepository
import com.example.call_mapbox_api.data.repository.SearchListRepository
import com.example.call_mapbox_api.model.EvPointDetails
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext appContext: Context): EvPointDataBase {
        return Room.databaseBuilder(
            appContext,
            EvPointDataBase::class.java,
            "logging.db"
        ).build()
    }

    @Provides
    @Singleton
    suspend fun provideOpenMapApi(openMapApi: OpenMapApi): List<EvPointDetails> {
        return openMapApi.getMaxResults()
    }

    @Provides
    fun provideSearchListUseCase(searchListRepository:
                                 SearchListRepository): ISearchListRepository {
        return searchListRepository
    }

    @Provides
    @Singleton
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): OpenMapApi = retrofit.create(OpenMapApi::class.java)

    @Provides
    fun provideEvPointsDao(database: EvPointDataBase): EvPointsDao {
        return database.evPointsDao()
    }

    @Provides
    @Singleton
    fun provideRetrofitResult(): Retrofit {
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
}
