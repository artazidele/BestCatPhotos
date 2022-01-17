package com.example.bestcatphotos

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bestcatphotos.CatApi
import com.example.bestcatphotos.model.CatPhoto
import com.example.bestcatphotos.model.Message
import com.example.bestcatphotos.model.Vote
import com.example.bestcatphotos.view.CatPhotoFragment
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class CatApiStatus { LOADING, ERROR, DONE }

class CatPhotoViewModel : ViewModel() {
    private val _status = MutableLiveData<CatApiStatus>()
    val status: LiveData<CatApiStatus> = _status
    private val _photos = MutableLiveData<List<CatPhoto>>()
    val photos: LiveData<List<CatPhoto>> = _photos
    fun getCatPhotos(count: String) {
        viewModelScope.launch {
            _status.value = CatApiStatus.LOADING
            try {
                _photos.value = CatApi.retrofitService.getPhotos(count)
                _status.value = CatApiStatus.DONE
            } catch (e: Exception) {
                _status.value = CatApiStatus.ERROR
                _photos.value = listOf()
            }
        }
    }

    fun makeVote(vote: Vote, onResult: (Vote?) -> Unit) {
        CatApi.retrofitService.makeVote(vote).enqueue(
            object : Callback<Message> {
                override fun onFailure(call: Call<Message>, t: Throwable) {
                    onResult(null)
                }

                override fun onResponse(call: Call<Message>, response: Response<Message>) {
                    val addedVote = vote
                    onResult(addedVote)
                }
            }
        )
    }
}