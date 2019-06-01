package com.example.chatting.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "uri")
data class UriBean (val uri:String){

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uri_id")
    var id:Int = 0
}