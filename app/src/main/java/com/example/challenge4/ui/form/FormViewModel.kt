package com.example.challenge4.ui.form

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge4.data.local.database.dao.FriendshipDao
import com.example.challenge4.data.local.database.entity.FriendshipEntity
import kotlinx.coroutines.launch

class FormViewModel (
    val database : FriendshipDao, application: Application) : AndroidViewModel(application){
    fun insertFriendship(friendship: FriendshipEntity) {
        viewModelScope.launch {
            getDataFromDatabase(friendship)
        }
    }

    private suspend fun getDataFromDatabase(friendship: FriendshipEntity) {
        database.insertFriendship(friendship)
    }
}