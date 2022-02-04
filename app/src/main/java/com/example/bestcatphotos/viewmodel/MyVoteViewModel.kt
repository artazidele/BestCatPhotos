package com.example.bestcatphotos.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.*
import com.example.bestcatphotos.CatApi
import com.example.bestcatphotos.CatApiStatus
import com.example.bestcatphotos.model.*
import com.example.bestcatphotos.view.MyVoteFragment
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class VoteStatus { LOADING, ERROR, DONE }

class MyVoteViewModel : ViewModel() {

//    private val _list = MutableLiveData<Task<QuerySnapshot>>()//MutableLiveData<List<Message>>()
//    val list: LiveData<Task<QuerySnapshot>> = _list

    private val _list = MutableLiveData<ArrayList<MessageF>>()//MutableLiveData<List<Message>>()
    val list: LiveData<ArrayList<MessageF>> = _list
    private val _querySnap = MutableLiveData<Task<QuerySnapshot>>()
    val querySnapshot: LiveData<Task<QuerySnapshot>> = _querySnap

    private val _listF = MutableLiveData<List<MessageF>>()
    val listF: LiveData<List<MessageF>> = _listF



    private val _votes = MutableLiveData<List<MyVote>>()
    val votes: LiveData<List<MyVote>> = _votes
    var photo = PhotoResponse("")
    private val _status = MutableLiveData<VoteStatus>()
    val status: LiveData<VoteStatus> = _status
    fun getMyVotes(userId: String) {
        _status.value = VoteStatus.LOADING
        viewModelScope.launch {
            try {
//                _list.value = FirebaseMessage().getAllMessages()
                _votes.value = CatApi.retrofitService.getMyVotes(userId)
                _status.value = VoteStatus.DONE
            } catch (e: Exception) {
                _status.value = VoteStatus.ERROR
                _votes.value = listOf()
            }
        }
    }

    fun getMyMessages() {
        _status.value = VoteStatus.LOADING
        viewModelScope.launch {
            try {
                FirebaseMessage().updateMessages()
//                _querySnap.value = FirebaseMessage().getQuerySnap()
//                _list.value = FirebaseMessage().getAllMessages()


//                _votes.value = CatApi.retrofitService.getMyVotes(userId)
//                _status.value = VoteStatus.DONE
            } catch (e: Exception) {
//                _list.value = arrayListOf()



//                _status.value = VoteStatus.ERROR
//                _votes.value = listOf()
            }
        }
    }

    fun printSnap() {
        Log.d(TAG, "QUERY QUERY QUERY" + querySnapshot.toString())
    }

    fun printMessagesMessage(message: String) {
//        list.observe()
        if (list != null) {
            Log.d(TAG, message)
        }
    }


    fun updateMessagesTrue(listMF: List<MessageF>) {
        _listF.value = listMF
        Log.d(TAG, "POSITIVE POSITIVE POSITIVE")
    }
    fun notUpdateMessages() {
        _listF.value = listOf()
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
