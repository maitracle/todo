package com.teamsparta.todo.todocards.dtos

import com.teamsparta.todo.todocards.TodoCard
import com.teamsparta.todo.users.User

data class UpdateTodoCardArguments(
    val id: Long?,
    val title: String,
    val content: String,
) {
    fun to(user: User): TodoCard {
        return TodoCard(
            id = id,
            title = title,
            content = content,
            author = user,
        )
    }
}
