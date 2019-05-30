package com.example.chatting.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NewsDao {

    @Query("SELECT * FROM news LIMIT 1")
    fun getLast():NewsBean

    @Query("SELECT * FROM news LIMIT 50")
    fun getAll():List<NewsBean>

    @Insert
    fun insertNews(news:NewsBean)

    @Insert
    fun insertNewsList(newsList: List<NewsBean>)

    @Query("SELECT id FROM news LIMIT 1")
    fun getLastId():Int

    @Query("SELECT * FROM news WHERE id >= :begin AND id <= :end")
    fun getSomeNews(begin:Int,end:Int):List<NewsBean>

}