package com.teamsparta.todo.replies

import com.teamsparta.todo.replies.dtos.CreateReplyArguments
import com.teamsparta.todo.replies.dtos.ReplyDto

interface ReplyService {
    fun createReply(createReplyArguments: CreateReplyArguments): ReplyDto
}