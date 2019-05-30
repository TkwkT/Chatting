package com.example.chatting.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatting.constdata.TYPE_TEXT
import com.example.chatting.model.ChattingModel
import com.example.chatting.room.NewsBean
import java.util.*

class ChattingViewModel:ViewModel(){
    val newsList = MutableLiveData<List<NewsBean>>()
    private val newsT = ArrayList<NewsBean>()

    fun getAll(context: Context){
        ChattingModel.getAll(context){
            newsT.addAll(it)
            newsList.value = newsT
        }
    }

    fun getNews(context: Context,editText: String){
        val newsData = NewsBean(editText,true, TYPE_TEXT)
        newsT.add(newsData)
        newsList.value = newsT
        ChattingModel.createRequestData(context,newsData){
            newsT.addAll(it)
            newsList.value = newsT
        }
    }









}