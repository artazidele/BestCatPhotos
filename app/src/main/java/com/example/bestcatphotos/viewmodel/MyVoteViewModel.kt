package com.example.bestcatphotos.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bestcatphotos.CatApi
import com.example.bestcatphotos.model.DeletedVoteMessage
import com.example.bestcatphotos.model.Message
import com.example.bestcatphotos.model.MyVote
import com.example.bestcatphotos.model.Vote
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyVoteViewModel : ViewModel() {
    private val _votes = MutableLiveData<List<MyVote>>()
    val votes: LiveData<List<MyVote>> = _votes
    fun getMyVotes() {
        viewModelScope.launch {
            try {
                _votes.value = CatApi.retrofitService.getMyVotes("test2")
            } catch (e: Exception) {
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

                override fun onResponse(call: Call<DeletedVoteMessage>, response: Response<DeletedVoteMessage>) {
                    val deletedVote = myVote
                    onResult(deletedVote)
                }
            }
        )
    }
}