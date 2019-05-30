package com.example.chatting.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ChattingService {

    @POST("openapi/api/v2")
    fun getNews(@Body request:ChattingRequestBean):Call<ChattingPostBackBean>

}