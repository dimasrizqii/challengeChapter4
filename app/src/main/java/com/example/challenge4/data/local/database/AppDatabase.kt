package com.example.challenge4.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.challenge4.data.local.database.dao.AccountDao
import com.example.challenge4.data.local.database.dao.FriendshipDao
import com.example.challenge4.data.local.database.entity.AccountEntity
import com.example.challenge4.data.local.database.entity.FriendshipEntity

@Database(entities = [AccountEntity::class, FriendshipEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDatabaseDao() : AccountDao
    abstract fun friendshipDatabaseDao() : FriendshipDao

    companion object {

        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "challenge4.db").build()
                }
                return instance
            }
        }
    }
}