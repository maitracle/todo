package com.teamsparta.todo.replies.dtos

data class UpdateReplyArguments(
    val id: Long?,
    val content: String,
    val authorName: String,
    val password: String,
)
