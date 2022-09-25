package com.example.challenge4.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.challenge4.data.local.database.dao.FriendshipDao
import com.example.challenge4.data.local.database.entity.FriendshipEntity
import kotlinx.coroutines.launch

class HomeViewModel (
    val database : FriendshipDao, application: Application) : AndroidViewModel(application){
    fun readFriendship() : MutableLiveData<List<FriendshipEntity>> {
        val dummy = MutableLiveData <List<FriendshipEntity>>()
        viewModelScope.launch {
            dummy.value = getDataFromDatabase()
        }
        return dummy
    }

    fun deleteFriendship(friendship: FriendshipEntity){
        viewModelScope.launch {
            database.deleteFriendship(friendship)
        }
    }

    private suspend fun getDataFromDatabase() : List<FriendshipEntity> {
        return database.readFriendship()
    }
}