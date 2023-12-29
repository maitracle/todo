package com.teamsparta.todo.replies.dtos

import com.teamsparta.todo.replies.Reply

class ReplyDto(
    var id: Long?,
    val content: String,
    val authorName: String,
    var todoCardId: Long,
) {
    companion object {
        fun from(reply: Reply): ReplyDto {
            return ReplyDto(
                id = reply.id,
                content = reply.content,
                authorName = reply.authorName,
                todoCardId = reply.todoCard.id ?: throw Exception("target todo card is not persisted"),
            )
        }
    }
}
