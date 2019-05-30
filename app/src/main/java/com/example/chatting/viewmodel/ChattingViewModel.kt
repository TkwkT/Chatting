package com.example.chatting.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatting.room.AppDatabase
import com.example.chatting.utils.runOnNewThread

class ChattingViewModel :ViewModel(){

    val newsLast = MutableLiveData<String>()

    fun getLast(context: Context){
        runOnNewThread {
            val db =  AppDatabase.getInstance(context)
            val news = db.newsDao().getLast().news
            if (news.isEmpty()){
                newsLast.value ="   "
            }else{
                newsLast.value = news
            }
        }
    }
}