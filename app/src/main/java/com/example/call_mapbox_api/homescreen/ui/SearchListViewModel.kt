package com.example.call_mapbox_api.homescreen.ui


import com.example.call_mapbox_api.model.EvPointDetails
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.call_mapbox_api.MyApplication
import com.example.call_mapbox_api.domain.SearchListUseCase
import com.example.call_mapbox_api.homescreen.data.SearchRecycleAdapter
import com.example.call_mapbox_api.util.ItemDataConverter


class SearchListViewModel(
    //private val searchListRepository: SearchListRepository,
    private val searchListUseCase: SearchListUseCase
) : ViewModel() {

    var selectedPoint: ItemDataConverter? = null
    private var adapter: SearchRecycleAdapter? = null

    suspend fun getAdapter(): SearchRecycleAdapter? {
        val getListOfItems = searchListUseCase()

        adapter = getListOfItems.value?.let {
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
        return adapter
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