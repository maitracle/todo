package com.teamsparta.todo.todocards

import com.teamsparta.todo.todocards.dtos.CreateTodoCardArguments
import com.teamsparta.todo.todocards.dtos.TodoCardDto

interface TodoCardService {
    fun createTodoCard(createTodoCardArguments: CreateTodoCardArguments): TodoCardDto
}
