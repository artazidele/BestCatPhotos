package com.example.bestcatphotos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bestcatphotos.CatApi
import com.example.bestcatphotos.model.CatPhoto
import kotlinx.coroutines.launch

enum class CatApiStatus { LOADING, ERROR, DONE }
//enum class PhotoReady {YES, NO}


class CatPhotoViewModel : ViewModel() {
    private val _status = MutableLiveData<CatApiStatus>()
    val status: LiveData<CatApiStatus> = _status
//    private val _ready = MutableLiveData<PhotoReady>()
//    val ready: LiveData<PhotoReady> = _ready
    private val _photos = MutableLiveData<List<CatPhoto>>()
    val photos: LiveData<List<CatPhoto>> = _photos
    fun getCatPhotos(count: String) {
        viewModelScope.launch {
            _status.value = CatApiStatus.LOADING
//            _ready.value = PhotoReady.YES
            try {
                _photos.value = CatApi.retrofitService.getPhotos(count)
                _status.value = CatApiStatus.DONE
            } catch (e: Exception) {
                _status.value = CatApiStatus.ERROR
                _photos.value = listOf()
            }
        }
    }
}