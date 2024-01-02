package com.teamsparta.todo.todocards

import com.teamsparta.todo.todocards.dtos.CreateTodoCardArguments
import com.teamsparta.todo.todocards.dtos.RetrieveTodoCardDto
import com.teamsparta.todo.todocards.dtos.TodoCardDto
import com.teamsparta.todo.todocards.dtos.UpdateTodoCardArguments

interface TodoCardService {
    fun createTodoCard(createTodoCardArguments: CreateTodoCardArguments): TodoCardDto
    fun findById(id: Long): RetrieveTodoCardDto?
    fun findAll(sort: String?): List<TodoCardDto>
    fun updateTodoCard(todoCardArguments: UpdateTodoCardArguments): TodoCardDto
    fun deleteTodoCard(id: Long)
    fun completeTodoCard(id: Long)
}
