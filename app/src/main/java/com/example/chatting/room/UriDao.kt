package com.example.chatting.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UriDao {

    @Insert
    fun insertUri(uriBean: UriBean)

    @Query("SELECT * FROM uri LIMIT 1")
    fun getLast():UriBean

    @Query("SELECT * FROM uri")
    fun getAllUri():List<UriBean>
}