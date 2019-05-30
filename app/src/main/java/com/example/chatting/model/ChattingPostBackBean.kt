package com.example.chatting.model

data class ChattingPostBackBean(
    var results: List<Result>
) {
    data class Result(
        var groupType: Int,
        var resultType: String,
        var values: Values
    ) {
        data class Values(
            var text: String
        )
    }
}