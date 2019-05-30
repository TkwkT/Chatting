package com.example.chatting.holder

import android.view.View
import com.example.chatting.constdata.TYPE_IMAGE
import com.example.chatting.databinding.ItemChattingNewsBinding
import com.example.chatting.room.NewsBean
import com.example.chatting.utils.GlideUtil

class ChattingHolder(private val binding:ItemChattingNewsBinding) :BaseHolder(binding){

    override fun <T> bind(t: T) {
        if (t is NewsBean){
            if(t.isUser){
                binding.robotGroup.visibility = View.GONE
                binding.userGroup.visibility = View.VISIBLE
                binding.tvUserNews.text = t.news
            }else{
                binding.userGroup.visibility = View.GONE
                binding.robotGroup.visibility = View.VISIBLE
                if (t.type == TYPE_IMAGE){
                    GlideUtil.getImageView(context,t.news,binding.ivRobotNews)
                }else{
                    binding.tvRobotNews.text = t.news
                }
            }
        }
    }
}