package com.example.call_mapbox_api

import android.app.Application
import android.content.Context
import com.example.call_mapbox_api.domain.SearchListUseCase
import com.example.call_mapbox_api.util.AppContainer

class MyApplication : Application() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

    fun getMyApp(): SearchListUseCase {
        return AppContainer().getSearchListUseCase()
    }
}