package com.example.chatting.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.chatting.holder.BaseHolder

open class BaseAdapter<T>(callback: DiffUtil.ItemCallback<T>,val onCreateViewHolderHandler:(ViewGroup, Int) -> BaseHolder,val onBindViewHolderHandler:(BaseHolder, Int) -> Unit):ListAdapter<T,BaseHolder>(callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = onCreateViewHolderHandler(parent,viewType)

    override fun onBindViewHolder(holder: BaseHolder, position: Int) = onBindViewHolderHandler(holder,position)

}