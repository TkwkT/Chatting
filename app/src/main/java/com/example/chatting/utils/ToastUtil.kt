package com.example.chatting.utils

import android.content.Context
import android.widget.Toast

object ToastUtil{

    fun setToast(context:Context,msg:String){
        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show()
    }
}