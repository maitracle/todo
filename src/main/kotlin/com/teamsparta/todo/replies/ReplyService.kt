package com.teamsparta.todo.replies

import com.teamsparta.todo.replies.dtos.CreateReplyArguments
import com.teamsparta.todo.replies.dtos.DeleteReplyArguments
import com.teamsparta.todo.replies.dtos.ReplyDto
import com.teamsparta.todo.replies.dtos.UpdateReplyArguments

interface ReplyService {
    fun createReply(createReplyArguments: CreateReplyArguments): ReplyDto
    fun updateReply(updateReplyArguments: UpdateReplyArguments): ReplyDto
    fun deleteReply(deleteReplyArguments: DeleteReplyArguments)
}
