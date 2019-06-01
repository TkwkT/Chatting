package com.example.chatting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatting.adapter.ChattingInterfaceAdapter
import com.example.chatting.databinding.ActivityChattingBinding
import com.example.chatting.viewmodel.ChattingInterfaceViewModel

class ChattingInterfaceActivity : AppCompatActivity() {

    private lateinit var adapter:ChattingInterfaceAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ChattingInterfaceAdapter()
        val binding = DataBindingUtil.setContentView<ActivityChattingBinding>(this,R.layout.activity_chatting)
        val viewModel = ViewModelProviders.of(this).get(ChattingInterfaceViewModel()::class.java)
        init(binding,viewModel)
    }

    fun init(binding: ActivityChattingBinding, viewModel: ChattingInterfaceViewModel){
        binding.rvChattingNews.layoutManager = LinearLayoutManager(this)
        binding.rvChattingNews.adapter = adapter
        viewModel.getAll(this){
            adapter.freshNews(it)
        }
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

    fun buttonOnClick(binding: ActivityChattingBinding, interfaceViewModel: ChattingInterfaceViewModel){
        binding.btSendNews.setOnClickListener {
            val text = binding.etChattingNews.text.toString()
            interfaceViewModel.getNews(this,text)
            submit(interfaceViewModel,binding)
            binding.etChattingNews.setText("")
        }
    }

    fun submit(viewModel: ChattingInterfaceViewModel,binding: ActivityChattingBinding){
        viewModel.newsList.observe({
            lifecycle
        },{
            adapter.freshNews(it)
            binding.rvChattingNews.scrollToPosition(it.size - 1)
        })
    }

}
