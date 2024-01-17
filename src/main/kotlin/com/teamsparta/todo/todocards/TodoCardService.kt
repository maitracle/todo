package com.teamsparta.todo.todocards

import com.teamsparta.todo.todocards.dtos.CreateTodoCardArguments
import com.teamsparta.todo.todocards.dtos.RetrieveTodoCardDto
import com.teamsparta.todo.todocards.dtos.TodoCardDto
import com.teamsparta.todo.todocards.dtos.UpdateTodoCardArguments
import com.teamsparta.todo.users.User

interface TodoCardService {
    fun createTodoCard(createTodoCardArguments: CreateTodoCardArguments, author: User): TodoCardDto
    fun findById(id: Long): RetrieveTodoCardDto?
    fun findAll(authorId: Long?, sort: String?): List<TodoCardDto>
    fun updateTodoCard(todoCardArguments: UpdateTodoCardArguments, user: User): TodoCardDto
    fun deleteTodoCard(id: Long)
    fun completeTodoCard(id: Long)
}
