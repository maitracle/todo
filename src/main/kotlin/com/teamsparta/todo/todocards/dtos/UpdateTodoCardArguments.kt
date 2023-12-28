package com.teamsparta.todo.todocards.dtos

import com.teamsparta.todo.todocards.TodoCard

data class UpdateTodoCardArguments(
    val id: Long?,
    val title: String,
    val content: String,
    val authorName: String,
) {
    fun to(): TodoCard {
        return TodoCard(
            id = id,
            title = title,
            content = content,
            authorName = authorName,
        )
    }
}
