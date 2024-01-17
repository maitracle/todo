package com.teamsparta.todo.todocards.dtos

import com.teamsparta.todo.replies.dtos.ReplyDto
import com.teamsparta.todo.todocards.TodoCard
import java.time.ZonedDateTime

data class TodoCardDto(
    val id: Long?,
    val title: String,
    val content: String,
    val authorName: String,
    val isCompleted: Boolean,
    val createdAt: ZonedDateTime,
    val replies: List<ReplyDto>,
) {
    companion object {
        fun from(todoCard: TodoCard): TodoCardDto {
            return TodoCardDto(
                id = todoCard.id,
                title = todoCard.title,
                content = todoCard.content,
                authorName = todoCard.author.username,
                isCompleted = todoCard.isCompleted,
                createdAt = todoCard.createdAt,
                replies = todoCard.replies.map { ReplyDto.from(it) }
            )
        }
    }
}