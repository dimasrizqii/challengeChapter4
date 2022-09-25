package com.example.challenge4.data.local.database.dao

import androidx.room.*
import com.example.challenge4.data.local.database.entity.FriendshipEntity

@Dao
interface FriendshipDao {
    @Query("SELECT * FROM FRIENDSHIP_DATABASE")
    suspend fun readFriendship(): List<FriendshipEntity>

    @Insert
    suspend fun insertFriendship(friendship: FriendshipEntity)

    @Update
    suspend fun updateFriendship(friendship: FriendshipEntity)

    @Delete
    suspend fun deleteFriendship(friendship: FriendshipEntity)
}