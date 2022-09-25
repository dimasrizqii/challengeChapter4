package com.example.challenge4.ui.form

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challenge4.data.local.database.dao.FriendshipDao
import com.example.challenge4.ui.home.HomeViewModel

class FormViewModelFactory(
    private val dataSource: FriendshipDao,
    private val application: Application
) : ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormViewModel::class.java)){
            return FormViewModel(dataSource, application) as T
        }
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}