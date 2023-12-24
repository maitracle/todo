package com.teamsparta.todo.todocards.dtos

import com.teamsparta.todo.todocards.TodoCard

data class CreateTodoCardArguments(
    val title: String,
    val content: String,
    val authorName: String,
) {
    fun to(): TodoCard {
        return TodoCard(
            title = title,
            content = content,
            authorName = authorName,
        )
    }
}
