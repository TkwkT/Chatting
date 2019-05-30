package com.example.chatting.enums

import androidx.annotation.StringDef


const val CHATTING = "聊天"
const val PERSONAL = "个人"


@Retention(AnnotationRetention.SOURCE)
@StringDef(
    CHATTING,
    PERSONAL
)
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.EXPRESSION, AnnotationTarget.VALUE_PARAMETER)
annotation class TagEnum()

