package com.example.chatting.viewmodel

import android.content.Context
import android.database.Cursor
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.chatting.model.PersonalModel
import com.example.chatting.room.AppDatabase
import com.example.chatting.room.UriBean
import com.example.chatting.utils.ToastUtil
import com.example.chatting.utils.runOnNewThread

class PersonalViewModel :ViewModel(){

    fun removeNews(context: Context){
        PersonalModel.getInstance().removeNews(context)
        ToastUtil.setToast(context,"数据清除成功！！")
    }

    fun insertUri(context: Context,uriBean:UriBean){
        runOnNewThread {
            AppDatabase.getInstance(context).uriDao().insertUri(uriBean)
            AppDatabase.getInstance(context).newsDao().updateUri(uriBean.uri)
        }
    }
}