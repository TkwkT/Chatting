package com.example.chatting.holder

import android.content.Intent
import com.example.chatting.ChattingInterfaceActivity
import com.example.chatting.databinding.ItemSingleChattingBinding

class ChattingHolder(private val binding: ItemSingleChattingBinding) :BaseHolder(binding){

    override fun <T> bind(t: T) {
        binding.cvSingleChatting.setOnClickListener {
            val intent = Intent(context,ChattingInterfaceActivity::class.java)
            context.startActivity(intent)
        }
    }

}