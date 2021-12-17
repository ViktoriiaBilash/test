package net.pet.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.pet.myapplication.model.VideoItemUI
import net.pet.myapplication.usecases.GetVideoResponseUseCase
import java.lang.Exception

class VideoViewModel (private val responseUseCase: GetVideoResponseUseCase) : ViewModel() {
    private val _data: MutableLiveData<List<VideoItemUI>> = MutableLiveData()
    val data: LiveData<List<VideoItemUI>> = _data

    private val _errorEvent: MutableLiveData<Exception> = MutableLiveData()
    val errorEvent: LiveData<Exception> = _errorEvent

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val newData = responseUseCase.invoke()
                _data.postValue(newData)
            }catch (e: Exception){
                _errorEvent.postValue(e)
                e.printStackTrace()
            }
        }
    }
}