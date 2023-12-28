package com.teamsparta.todo.todocards

import com.teamsparta.todo.todocards.dtos.CreateTodoCardArguments
import com.teamsparta.todo.todocards.dtos.TodoCardDto
import com.teamsparta.todo.todocards.dtos.UpdateTodoCardArguments

interface TodoCardService {
    fun createTodoCard(createTodoCardArguments: CreateTodoCardArguments): TodoCardDto
    fun findById(id: Long): TodoCardDto?
    fun findAll(): List<TodoCardDto>
    fun updateTodoCard(todoCardArguments: UpdateTodoCardArguments): TodoCardDto
}
