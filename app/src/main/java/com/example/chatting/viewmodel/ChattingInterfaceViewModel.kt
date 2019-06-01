package com.example.chatting.viewmodel

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.chatting.R
import com.example.chatting.constdata.TYPE_TEXT
import com.example.chatting.model.ChattingInterfaceModel
import com.example.chatting.room.NewsBean
import com.example.chatting.utils.GlideUtil
import java.util.*
import kotlin.collections.ArrayList

class ChattingInterfaceViewModel:ViewModel(){
    val newsList = MutableLiveData<List<NewsBean>>()
    private val newsT = ArrayList<NewsBean>()

    fun getAll(context: Context,callback:(List<NewsBean>) -> Unit){
        ChattingInterfaceModel.getInstance().getAll(context){
            Log.d("aaa",it.size.toString())
            newsT.addAll(it)
            newsList.value = newsT
            callback(it)
        }
    }

    fun getNews(context: Context,editText: String){
        val newsData = NewsBean(editText,true, TYPE_TEXT)
        ChattingInterfaceModel.getInstance().getUri(context){
            newsData.uri = it.uri
            newsT.add(newsData)
            newsList.value = newsT
        }
        ChattingInterfaceModel.getInstance().createRequestData(context,newsData){
            newsT.addAll(it)
            newsList.value = newsT
        }
    }









}