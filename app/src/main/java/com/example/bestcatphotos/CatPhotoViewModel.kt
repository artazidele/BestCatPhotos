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
    private val _message = MutableLiveData<Message>()
    val message: MutableLiveData<Message> = _message
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

    fun makePositiveVote(vote: Vote, onResult: (Vote?) -> Unit) {
//        viewModelScope.launch {
//            try {
//                _message.value = CatApi.retrofitService.makeVote(vote)
//                //_status.value = CatApiStatus.DONE
//            } catch (e: Exception) {
////                _status.value = CatApiStatus.ERROR
////                _photos.value = listOf()
//            }
//        }




        CatApi.retrofitService.makeVote(vote).enqueue(
            object : Callback<Vote> {
                override fun onFailure(call: Call<Vote>, t: Throwable) {
                    onResult(null)
                    Log.v(TAG, t.message.toString())
                    Log.v(TAG, t.stackTraceToString())
                }

                override fun onResponse(call: Call<Vote>, response: Response<Vote>) {
                    val addedVote = response.body()
                    onResult(addedVote)
                }
            }
        )
    }

//    fun makePositiveVote(userId: String, imageId: String, value: Int) {
//        val vote = Vote(
//            imageId,
//            userId,
//            1
//        )
//        val call = CatApi.retrofitService.makeVote(vote)
//        call.enqueue(object: Callback<Message> {
//            override fun onFailure(call: Call<Message>, t: Throwable) {
//                //TODO("Not yet implemented")
//                Log.v(TAG, "onFailure")
//            }
//
//            override fun onResponse(call: Call<Message>, response: Response<Message>) {
//                //TODO("Not yet implemented")
//                if (response.isSuccessful) {
//                    Log.v(TAG, "isSuccessful")
//                } else {
//                    Log.v(TAG, "notSuccessful")
//                }
//            }
//        })
//    }
//    fun makeVote(context: Context, userId: String, imageId: String, value: Int) {
//        val vote = Vote(
//            imageId,
//            userId,
//            1
//        )
//        viewModelScope.launch {
////            _status.value = CatApiStatus.LOADING
//            try {
//                CatApi.retrofitService.makeVote(vote)
//                CatPhotoFragment().showPositive()



//                CatApi.retrofitService
//                    .makeVote(imageId, userId, value) //, "45831cb5-c900-48d4-b21d-b15ce3d1fc51")


//                _message.value = CatApi.retrofitService
//                    .makeVote(userId, imageId,value, "45831cb5-c900-48d4-b21d-b15ce3d1fc51")
//                _status.value = CatApiStatus.DONE
//                Log.v(TAG, "POSITIVE")
//                CatPhotoFragment().showPositive()
//                Toast.makeText(context, "You rated positive.", Toast.LENGTH_SHORT).show()
//            } catch (e: Exception) {
////                _status.value = CatApiStatus.ERROR
////                _photos.value = listOf()
////                Log.v(TAG, e.stackTrace.toString())
////                Log.v(TAG, e.cause.toString())
////                Log.v(TAG, e.message.toString())
//                Log.v(TAG, e.localizedMessage.toString())
//                Log.v(TAG, e.printStackTrace().toString())
//            }
//        }
//    }
//    fun getVotedPhotos(userId: String) {
//        viewModelScope.launch {
//            _status.value = CatApiStatus.LOADING
//            try {
//                _photos.value = CatApi.retrofitService.getVotes(userId, "45831cb5-c900-48d4-b21d-b15ce3d1fc51")
//                _status.value = CatApiStatus.DONE
//            } catch (e: Exception) {
//                _status.value = CatApiStatus.ERROR
//                _photos.value = listOf()
//            }
//        }
//    }

}