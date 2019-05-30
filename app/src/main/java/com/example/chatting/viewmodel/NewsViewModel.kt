package com.example.chatting.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.example.chatting.constdata.TYPE_IMAGE
import com.example.chatting.room.NewsBean

class NewsViewModel(private val newsBean: NewsBean) {
    val news = ObservableField<String>(newsBean.news)
    val isUser = ObservableBoolean(newsBean.isUser)
    val isImage = ObservableBoolean(false)

    init {
        if (newsBean.type == TYPE_IMAGE){
            isImage.set(true)
        }else{
            isImage.set(false)
        }
    }
}