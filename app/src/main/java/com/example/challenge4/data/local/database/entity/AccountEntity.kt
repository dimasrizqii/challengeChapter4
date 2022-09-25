package com.example.challenge4.data.local.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "account_database")
data class AccountEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "user_name")
    var userName: String? = "",
    @ColumnInfo(name = "email")
    var email: String? = "",
    @ColumnInfo(name = "password")
    var password: String?
) : Parcelable