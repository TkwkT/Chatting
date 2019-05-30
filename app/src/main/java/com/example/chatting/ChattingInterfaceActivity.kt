package com.example.chatting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
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

    fun init(binding: ActivityChattingBinding, interfaceViewModel: ChattingInterfaceViewModel){
        interfaceViewModel.getAll(this)
        submit(interfaceViewModel)
        binding.rvChattingNews.adapter = adapter
        editTextListen(binding)
        buttonOnClick(binding,interfaceViewModel)
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
            submit(interfaceViewModel)
            binding.etChattingNews.setText("")
        }
    }

    fun submit(viewModel: ChattingInterfaceViewModel){
        viewModel.newsList.observe({
            lifecycle
        },{
            adapter.freshNews(it)
        })
    }

}
