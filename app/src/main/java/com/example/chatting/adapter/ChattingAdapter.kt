package com.example.chatting.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chatting.R
import com.example.chatting.databinding.ItemChattingNewsBinding
import com.example.chatting.databinding.ItemSingleChattingBinding
import com.example.chatting.holder.BaseHolder
import com.example.chatting.holder.ChattingHolder
import java.util.ArrayList

class ChattingAdapter : RecyclerView.Adapter<BaseHolder>(){

    private val robotList = ArrayList<String>()

    fun freshList(sth:String?){
        robotList.clear()
        if (sth != null) {
            robotList.add(sth)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        val binding = DataBindingUtil.inflate<ItemSingleChattingBinding>(LayoutInflater.from(parent.context), R.layout.item_single_chatting,parent,false)
        return ChattingHolder(binding)
    }

    override fun getItemCount(): Int {
        return robotList.size
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        holder.bind(robotList[position])
    }

}