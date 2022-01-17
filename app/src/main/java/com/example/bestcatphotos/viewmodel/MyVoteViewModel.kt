package com.example.bestcatphotos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bestcatphotos.CatApi
import com.example.bestcatphotos.model.MyVote
import kotlinx.coroutines.launch

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
}