package com.example.chatting.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chatting.R
import com.example.chatting.databinding.ItemChattingNewsBinding
import com.example.chatting.holder.BaseHolder
import com.example.chatting.holder.ChattingHolder
import com.example.chatting.holder.ChattingInterfaceHolder
import com.example.chatting.room.NewsBean

class ChattingInterfaceAdapter:RecyclerView.Adapter<BaseHolder>(){

    private val mDiffer = AsyncListDiffer<NewsBean>(this,NewsDiffCallback())

    fun freshNews(list:List<NewsBean>){
        mDiffer.submitList(list)
        notifyDataSetChanged()
        Log.d("aaa","notify")
    }

    fun getItem(position: Int):NewsBean{
        return mDiffer.currentList[position]
    }

    override fun getItemCount(): Int {
        return mDiffer.currentList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        val binding = DataBindingUtil.inflate<ItemChattingNewsBinding>(LayoutInflater.from(parent.context),R.layout.item_chatting_news,parent,false)
        return ChattingInterfaceHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private class NewsDiffCallback : DiffUtil.ItemCallback<NewsBean>() {

    override fun areItemsTheSame(oldItem: NewsBean, newItem: NewsBean): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NewsBean, newItem: NewsBean): Boolean {
        return oldItem == newItem
    }
}