package com.example.call_mapbox_api.ui.searchscreen

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.call_mapbox_api.MyApplication
import com.example.call_mapbox_api.domain.ISearchListUseCase
import com.example.call_mapbox_api.model.EvPointDetails
import com.example.call_mapbox_api.model.ItemDataConverter
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SearchListViewModel(
    private val searchListUseCase: ISearchListUseCase,
) : ViewModel() {

    var listOfItems = MutableLiveData<List<EvPointDetails>>()
    var connectionItems = MutableLiveData<ItemDataConverter>()

    init {
        viewModelScope.launch {
            // Trigger the flow and consume its elements using collect
            searchListUseCase.invoke()
                .catch { exception -> println(exception) }
                .collect { items ->
                    listOfItems.postValue(items)
                    // Update View with the latest favorite news

                }
        }
    }


    /*suspend fun getListUseCase() {
        searchListUseCase()
            .onEach { listOfItems.value = it }
            .catch { println(it) }
            .launchIn(viewModelScope)

    }*/

       /* searchListUseCase.invoke().collect{
                items -> listOfItems.postValue(items)
                    }
    }*/

    fun setDetailItems(item: ItemDataConverter) {
        connectionItems.value = item
    }

    fun getDetailItems(): MutableLiveData<ItemDataConverter> {
        return connectionItems
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