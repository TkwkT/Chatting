package com.example.chatting.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object GlideUtil {

    fun getImageView(context:Context,url:String,view:ImageView){
        Glide.with(context)
            .load(url)
            .into(view)
    }

}