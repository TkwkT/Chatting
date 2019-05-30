package com.example.chatting

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.chatting.adapter.ChattingAdapter
import com.example.chatting.databinding.ActivityChattingBinding
import com.example.chatting.room.NewsBean
import com.example.chatting.viewmodel.ChattingViewModel

class ChattingActivity : AppCompatActivity() {

    private lateinit var adapter:ChattingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ChattingAdapter()
        val binding = DataBindingUtil.setContentView<ActivityChattingBinding>(this,R.layout.activity_chatting)
        val viewModel = ViewModelProviders.of(this).get(ChattingViewModel()::class.java)
        init(binding,viewModel)
    }

    fun init(binding: ActivityChattingBinding,viewModel: ChattingViewModel){
        viewModel.getAll(this)
        submit(viewModel)
        binding.rvChattingNews.adapter = adapter
        editTextListen(binding)
        buttonOnClick(binding,viewModel)
    }

    fun editTextListen(binding: ActivityChattingBinding){
        binding.etChattingNews.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s.isNullOrEmpty()){
                    binding.btSendNews.isEnabled = false
                    binding.btSendNews.background = getDrawable(R.drawable.bg_botton_invisible)
                }else{
                    binding.btSendNews.isEnabled = true
                    binding.btSendNews.background = getDrawable(R.drawable.bg_botton_send)
                }
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()){
                    binding.btSendNews.isEnabled = false
                    binding.btSendNews.background = getDrawable(R.drawable.bg_botton_invisible)
                }else{
                    binding.btSendNews.isEnabled = true
                    binding.btSendNews.background = getDrawable(R.drawable.bg_botton_send)
                }
            }
        })
    }

    fun buttonOnClick(binding: ActivityChattingBinding, viewModel: ChattingViewModel){
        binding.btSendNews.setOnClickListener {
            val text = binding.etChattingNews.text.toString()
            viewModel.getNews(this,text)
            submit(viewModel)
            binding.etChattingNews.setText("")
        }
    }

    fun submit(viewModel: ChattingViewModel){
        viewModel.newsList.observe({
            lifecycle
        },{
            adapter.freshNews(it)
        })
    }

}
