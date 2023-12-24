package com.teamsparta.todo.todocards

import java.time.ZonedDateTime

data class TodoCardDto(
    val id: Long?,
    val title: String,
    val content: String,
    val authorName: String,
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
) {
    companion object {
        fun from(todoCard: TodoCard): TodoCardDto {
            return TodoCardDto(
                id = todoCard.id,
                title = todoCard.title,
                content = todoCard.content,
                authorName = todoCard.authorName,
                createdAt = todoCard.createdAt,
            )
        }
    }
}