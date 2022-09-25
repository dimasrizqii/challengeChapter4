package com.example.challenge4.data.local.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "friendship_database")
data class FriendshipEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo
    var friendName: String?,
    @ColumnInfo
    var friendshipStatus: String?
) : Parcelable
