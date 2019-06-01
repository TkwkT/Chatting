package com.example.chatting.room

import androidx.room.*

@Dao
interface NewsDao {

    @Query("SELECT * FROM news LIMIT 1")
    fun getLast():NewsBean

    @Query("SELECT * FROM news")
    fun getAll():List<NewsBean>

    @Insert
    fun insertNews(news:NewsBean)

    @Insert
    fun insertNewsList(newsList: List<NewsBean>)

    @Query("SELECT news_id FROM news LIMIT 1")
    fun getLastId():Int

    @Query("SELECT * FROM news WHERE news_id >= :begin AND news_id <= :end")
    fun getSomeNews(begin:Int,end:Int):List<NewsBean>

    @Query("DELETE FROM news")
    fun removeNews()

    @Query("UPDATE news SET uri = :uri")
    fun updateUri(uri:String)

}