package com.example.chatting.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NewsBean::class,UriBean::class],version = 1, exportSchema = false)
abstract class AppDatabase :RoomDatabase(){
    abstract fun newsDao():NewsDao
    abstract fun uriDao():UriDao

    companion object {

        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "db_chatting").build()
        }
    }

}