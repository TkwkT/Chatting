package com.example.chatting.holder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseHolder(viewDataBinding: ViewDataBinding):RecyclerView.ViewHolder(viewDataBinding.root){

    protected val context = viewDataBinding.root.context

    abstract fun <T>bind(t:T)
}