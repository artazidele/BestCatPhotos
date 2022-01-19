package com.example.bestcatphotos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bestcatphotos.CatApi
import com.example.bestcatphotos.CatApiStatus
import com.example.bestcatphotos.model.*
import com.example.bestcatphotos.view.MyVoteFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class VoteStatus { LOADING, ERROR, DONE }

class MyVoteViewModel : ViewModel() {
    private val _votes = MutableLiveData<List<MyVote>>()
    val votes: LiveData<List<MyVote>> = _votes
    var photo = PhotoResponse("")
    private val _status = MutableLiveData<VoteStatus>()
    val status: LiveData<VoteStatus> = _status
    fun getMyVotes(userId: String) {
        _status.value = VoteStatus.LOADING
        viewModelScope.launch {
            try {
                _votes.value = CatApi.retrofitService.getMyVotes(userId)
                _status.value = VoteStatus.DONE
            } catch (e: Exception) {
                _status.value = VoteStatus.ERROR
                _votes.value = listOf()
            }
        }
    }

    fun deleteVote(myVote: MyVote, onResult: (MyVote?) -> Unit) {
        CatApi.retrofitService.deleteVote(myVote.id).enqueue(
            object : Callback<DeletedVoteMessage> {
                override fun onFailure(call: Call<DeletedVoteMessage>, t: Throwable) {
                    onResult(null)
                }

                override fun onResponse(
                    call: Call<DeletedVoteMessage>,
                    response: Response<DeletedVoteMessage>
                ) {
                    val deletedVote = myVote
                    onResult(deletedVote)
                }
            }
        )
    }

    fun getPhoto(imageId: String, onResult: (PhotoResponse?) -> Unit) {
        CatApi.retrofitService.getPhotoForUrl(imageId).enqueue(
            object : Callback<PhotoResponse> {
                override fun onResponse(
                    call: Call<PhotoResponse>,
                    response: Response<PhotoResponse>
                ) {
                    photo = response.body()!!
                    onResult(photo)
                }

                override fun onFailure(call: Call<PhotoResponse>, t: Throwable) {
                    onResult(null)
                }
            }
        )
    }
}
