package com.example.chatting


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.chatting.adapter.ChattingAdapter
import com.example.chatting.databinding.FragmentChattingBinding
import com.example.chatting.viewmodel.ChattingInterfaceViewModel
import com.example.chatting.viewmodel.ChattingViewModel

class ChattingFragment : Fragment() {

    private lateinit var adapter:ChattingAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        adapter = ChattingAdapter()
        adapter.freshList(" ")
        val binding = DataBindingUtil.inflate<FragmentChattingBinding>(inflater,R.layout.fragment_chatting, container, false)
        val viewModel = ViewModelProviders.of(this).get(ChattingViewModel()::class.java)
        viewModel.getLast(requireContext())
        init(binding,viewModel)
        return binding.root
    }

    fun init(binding: FragmentChattingBinding,viewModel: ChattingViewModel){
        viewModel.newsLast.observe(viewLifecycleOwner, Observer {
            adapter.freshList(it)
        })
        binding.rvSingleChatting.adapter = adapter
    }

}
