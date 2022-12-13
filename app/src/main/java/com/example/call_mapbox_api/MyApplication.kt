package com.example.call_mapbox_api

import android.app.Application
import com.example.call_mapbox_api.data.local.EvPointDataBase
import com.example.call_mapbox_api.domain.SearchListUseCase
import com.example.call_mapbox_api.util.AppContainer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyApplication : Application() {

    fun getMyApp(): SearchListUseCase {
        return AppContainer().getSearchListUseCase()
    }
}