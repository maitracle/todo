package com.teamsparta.todo.replies.dtos

data class CreateReplyArguments(
    val content: String,
    val authorName: String,
    val password: String,
    val todoCardId: Long,
)
