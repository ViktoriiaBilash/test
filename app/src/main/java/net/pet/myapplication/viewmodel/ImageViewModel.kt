package net.pet.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.pet.myapplication.retrofit.repositoryImages.ApiRepository
import net.pet.myapplication.model.Image
import java.lang.Exception

class ImageViewModel(
    private val repository: ApiRepository
) : ViewModel() {

    private val _data: MutableLiveData<List<Image>> = MutableLiveData()
    val data: LiveData<List<Image>> = _data

    private val _errorEvent: MutableLiveData<Exception> = MutableLiveData()
    val errorEvent: LiveData<Exception> = _errorEvent

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val newData = repository.getAll()
                _data.postValue(newData)
            }catch (e: Exception){
                _errorEvent.postValue(e)
            }
        }
    }
}