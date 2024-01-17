package com.teamsparta.todo.todocards.dtos

import com.teamsparta.todo.todocards.TodoCard
import com.teamsparta.todo.users.User

data class CreateTodoCardArguments(
    val title: String,
    val content: String,
) {
    fun to(author: User): TodoCard {
        return TodoCard(
            title = title,
            content = content,
            author = author,
        )
    }
}
