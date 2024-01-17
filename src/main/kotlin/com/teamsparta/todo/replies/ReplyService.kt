package com.teamsparta.todo.replies

import com.teamsparta.todo.replies.dtos.CreateReplyArguments
import com.teamsparta.todo.replies.dtos.DeleteReplyArguments
import com.teamsparta.todo.replies.dtos.ReplyDto
import com.teamsparta.todo.replies.dtos.UpdateReplyArguments
import com.teamsparta.todo.users.User

interface ReplyService {
    fun createReply(createReplyArguments: CreateReplyArguments, user: User): ReplyDto
    fun updateReply(updateReplyArguments: UpdateReplyArguments, user: User): ReplyDto
    fun deleteReply(deleteReplyArguments: DeleteReplyArguments, user: User)
}
