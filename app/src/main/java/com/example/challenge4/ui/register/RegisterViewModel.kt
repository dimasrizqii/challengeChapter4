package com.example.challenge4.ui.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge4.data.local.database.dao.AccountDao
import com.example.challenge4.data.local.database.entity.AccountEntity
import kotlinx.coroutines.launch

class RegisterViewModel (
    val database : AccountDao, application: Application) : AndroidViewModel(application) {
    fun insertAccount(account: AccountEntity) {
        viewModelScope.launch {
            getDataFromDatabase(account)
        }
    }

    private suspend fun getDataFromDatabase(account: AccountEntity) {
        database.insertAccount(account)
    }

}