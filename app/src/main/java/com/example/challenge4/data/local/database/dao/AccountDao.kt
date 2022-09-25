package com.example.challenge4.data.local.database.dao

import androidx.room.*
import com.example.challenge4.data.local.database.entity.AccountEntity

@Dao
interface AccountDao {
    @Query("SELECT * FROM ACCOUNT_DATABASE WHERE EMAIL == :email")
    suspend fun readAccountByEmail(email: String): AccountEntity?

    @Insert
    suspend fun insertAccount(account: AccountEntity)
}