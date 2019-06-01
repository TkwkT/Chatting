package com.example.chatting.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsBean (
    val news:String,

    @ColumnInfo(name = "is_user")
    val isUser:Boolean,
    val type:String
){

    var uri:String = ""
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "news_id")
    var id:Int = 0


}