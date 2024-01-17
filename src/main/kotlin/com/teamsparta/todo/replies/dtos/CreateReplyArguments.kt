package com.teamsparta.todo.replies.dtos

data class CreateReplyArguments(
    val content: String,
    val todoCardId: Long,
)
