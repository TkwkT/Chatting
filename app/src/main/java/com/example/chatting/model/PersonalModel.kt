package com.example.chatting.model

import android.content.Context
import android.widget.Toast
import com.example.chatting.room.AppDatabase
import com.example.chatting.utils.ToastUtil
import com.example.chatting.utils.runOnNewThread

class PersonalModel {

    fun removeNews(context: Context){
        runOnNewThread {
            val db = AppDatabase.getInstance(context)
            db.newsDao().removeNews()
        }
    }

    companion object {

        @Volatile private var instance: PersonalModel? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: PersonalModel().also { instance = it }
        }
    }

}