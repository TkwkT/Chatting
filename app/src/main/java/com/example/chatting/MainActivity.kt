package com.example.chatting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.chatting.adapter.MainPagerAdapter
import com.example.chatting.databinding.ActivityMainBinding
import com.example.chatting.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var fragmentList:ArrayList<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        init(binding)
    }

    private fun init(binding:ActivityMainBinding){
        val chattingPager = binding.vpChatting
        val tagChatting = binding.tabChatting
        val viewModel = MainActivityViewModel()
        viewModel.initFragment()
        val chattingPagerAdapter = MainPagerAdapter(supportFragmentManager,viewModel.fragmentList)
        chattingPager.adapter = chattingPagerAdapter
        tagChatting.setupWithViewPager(chattingPager)
        for (i in 0 until viewModel.tabList.size) {
            tagChatting.getTabAt(i)?.text = viewModel.tabList[i]
        }
    }
}
