package com.example.call_mapbox_api.homescreen.ui

<<<<<<< HEAD
import androidx.lifecycle.MutableLiveData
=======
import androidx.lifecycle.*
import com.example.call_mapbox_api.homescreen.data.SearchListRepository
>>>>>>> parent of b0d731e (UseCase)
import com.example.call_mapbox_api.model.EvPointDetails
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.call_mapbox_api.MyApplication
<<<<<<< HEAD
import com.example.call_mapbox_api.domain.SearchListUseCase
import kotlinx.coroutines.launch

class SearchListViewModel(
    private val searchListUseCase: SearchListUseCase
=======


class SearchListViewModel(
    private val searchListRepository: SearchListRepository,
>>>>>>> parent of b0d731e (UseCase)
) : ViewModel() {

    var listOfItems = MutableLiveData<List<EvPointDetails>>()

    init {
        viewModelScope.launch {
            getListUseCase()
        }
    }

<<<<<<< HEAD
    suspend fun getListUseCase() {
        searchListUseCase.invoke().collect { items ->
=======
    suspend fun getElements() {
        return searchListRepository.getlatestList().collect { items ->
>>>>>>> parent of b0d731e (UseCase)
            listOfItems.postValue(items)
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