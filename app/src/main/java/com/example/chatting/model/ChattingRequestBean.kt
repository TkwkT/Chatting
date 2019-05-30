package com.example.chatting.model

data class ChattingRequestBean(
    var perception: Perception,
    var reqType: Int,
    var userInfo: UserInfo
) {
    data class UserInfo(
        var apiKey: String,
        var userId: String
    )

    data class Perception(
        var inputText: InputText
    ) {
        data class InputText(
            var text: String
        )
    }
}


