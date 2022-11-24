package com.example.call_mapbox_api.homescreen.ui

import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.*
import com.example.call_mapbox_api.homescreen.data.SearchListRepository
import com.example.call_mapbox_api.model.EvPointDetails
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.call_mapbox_api.MyApplication
import com.example.call_mapbox_api.R
import com.example.call_mapbox_api.domain.SearchListUseCase
import com.example.call_mapbox_api.homescreen.data.SearchRecycleAdapter
import com.example.call_mapbox_api.util.ItemDataConverter


class SearchListViewModel(
    //private val searchListRepository: SearchListRepository,
    private val searchListUseCase: SearchListUseCase
) : ViewModel() {

    //var listOfItems = MutableLiveData<List<EvPointDetails>>()

    // I do not think this would work like this !!!!!
    lateinit var selectedPoint: ItemDataConverter

    init {
        viewModelScope.launch {
            getElements()
        }
    }

    suspend fun getElements() {
        return searchListUseCase.getColletedItems()
    }


    // I need List<EvPointDetails> instead of "it" in SearchRecycleAdapter
    fun getAdapter(): SearchRecycleAdapter {
        searchListUseCase.listOfItems.apply {
            SearchRecycleAdapter(it, object : SearchRecycleAdapter.OnAdapterListener {
                override fun onClick(address: EvPointDetails) {
                    val AddressLine1 = address.AddressInfo?.AddressLine1
                    val AddressLine2 = address.AddressInfo?.AddressLine2
                    val Longitude = address.AddressInfo?.Longitude
                    val Latitude = address.AddressInfo?.Latitude
                    val Title = address.AddressInfo?.Title
                    val PostCode = address.AddressInfo?.Postcode
                    val Town = address.AddressInfo?.Town
                    val UsageCost = address.UsageCost
                    val NumberOfPoints = address.NumberOfPoints
                    val dataUpdate = address.DateLastStatusUpdate
                    val Connection = address.Connection

                    selectedPoint = ItemDataConverter(
                        AddressLine1,
                        AddressLine2,
                        Longitude,
                        Latitude,
                        Title,
                        PostCode,
                        Town,
                        UsageCost,
                        NumberOfPoints,
                        dataUpdate,
                        Connection
                    )
                }
            })
        }
    }

    //Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                return SearchListViewModel(
                    MyApplication().getMyApp(),
                ) as T
            }
        }
    }
}