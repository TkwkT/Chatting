package com.example.chatting.viewmodel

import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.chatting.ChattingFragment
import com.example.chatting.PersonalFragment
import com.example.chatting.enums.CHATTING
import com.example.chatting.enums.PERSONAL
import com.example.chatting.enums.TagEnum
import com.example.chatting.utils.ToastUtil

class MainActivityViewModel :ViewModel(){
    private val fragmentMap = HashMap<@TagEnum String,Fragment>()
    val fragmentList = ObservableArrayList<Fragment>()
    val tabList = ObservableArrayList<String>()

    init {
        fragmentMap.put(@TagEnum CHATTING,ChattingFragment())
        fragmentMap.put(@TagEnum PERSONAL,PersonalFragment())
    }

    fun initFragment(){
        fragmentList.add(ChattingFragment())
        tabList.add(@TagEnum CHATTING)
        fragmentList.add(PersonalFragment())
        tabList.add(@TagEnum PERSONAL)
    }

//    fun addFragment(context: Context,fragment: Fragment,@TagEnum tag:String){
//        if (tabList.size == 5){
//            ToastUtil.setToast(context,"已经设置够多了，不能再设置了")
//            return
//        }
//        if (tabList.contains(tag)){
//            ToastUtil.setToast(context,"这个页面已经设置了，换一个呗！！")
//            return
//        }
//        tabList.add(tag)
//        fragmentList.add(fragmentMap[tag])
//        ToastUtil.setToast(context,"设置成功")
//    }

    fun removeFragment(context: Context,@TagEnum tag:String){
        if (tag == CHATTING || tag == PERSONAL){
            ToastUtil.setToast(context,"这个页面是不可删除的")
            return
        }
        tabList.remove(tag)
        fragmentList.remove(fragmentMap[tag])
    }

}