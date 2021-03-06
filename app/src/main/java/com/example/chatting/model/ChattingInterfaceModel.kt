package com.example.chatting.model

import android.content.Context
import android.util.Log
import com.example.chatting.constdata.*
import com.example.chatting.http.ChattingNewsHttp
import com.example.chatting.room.AppDatabase
import com.example.chatting.room.NewsBean
import com.example.chatting.room.UriBean
import com.example.chatting.utils.runOnNewThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class ChattingInterfaceModel {

    private fun getResult(request:ChattingRequestBean):Call<ChattingPostBackBean>{
        val chattingNewsHttp = ChattingNewsHttp()
        return chattingNewsHttp
            .getBuilder()
            .create(ChattingService::class.java)
            .getNews(request)
    }

    private fun getRequest(context: Context, request:ChattingRequestBean, callback:(List<NewsBean>) -> Unit){
        getResult(request).enqueue(object :Callback<ChattingPostBackBean>{
            override fun onFailure(call: Call<ChattingPostBackBean>, t: Throwable) {
                throw t
            }

            override fun onResponse(call: Call<ChattingPostBackBean>, response: Response<ChattingPostBackBean>) {
                val newsList = ArrayList<NewsBean>()
                val postBack = response.body()
                for (i in 0 until postBack!!.results.size){
                    val news = postBack.results[i].values.text
                    var type = ""
                    when(postBack.results[i].resultType){
                        "text" ->type = TYPE_TEXT
                        "url" -> type = TYPE_URL
                        "voice" -> type = TYPE_VOICE
                        "video" -> type = TYPE_VIDEO
                        "image" -> type = TYPE_IMAGE
                        "news" -> type = TYPE_NEWS
                    }
                    val newsT = NewsBean(news,false,type)
                    newsList.add(newsT)
                }
                insertDB(context,newsList)
                callback(newsList)
            }
        })
    }

    fun createRequestData(context: Context, newsBean: NewsBean, callback:(List<NewsBean>) -> Unit){
        insertDB(context,newsBean)
        val inputText = ChattingRequestBean.Perception.InputText(newsBean.news)
        val perception = ChattingRequestBean.Perception(inputText)
        val userInfo = ChattingRequestBean.UserInfo(API_KEY, USER_ID)
        val chattingRequestData = ChattingRequestBean(userInfo = userInfo,perception = perception,reqType = 0)
        getRequest(context,chattingRequestData,callback)
    }

    fun getUri(context: Context,callback:(UriBean) -> Unit){
        runOnNewThread {
            val db = AppDatabase.getInstance(context)
            callback(db.uriDao().getLast())
        }
    }

    private fun insertDB(context: Context,newsList:List<NewsBean>){
        runOnNewThread {
            val db = AppDatabase.getInstance(context)
            db.newsDao().insertNewsList(newsList)
        }
    }

    private fun insertDB(context: Context,news:NewsBean){
        runOnNewThread {
            val db = AppDatabase.getInstance(context)
            db.newsDao().insertNews(news)
        }
    }

    fun getAll(context: Context,callback: (List<NewsBean>) -> Unit){
        runOnNewThread {
            val db = AppDatabase.getInstance(context)
            callback(db.newsDao().getAll())
        }
    }

    companion object {

        @Volatile private var instance: ChattingInterfaceModel? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: ChattingInterfaceModel().also { instance = it }
        }
    }
}