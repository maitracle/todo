package com.teamsparta.todo.todocards

import com.teamsparta.todo.todocards.dtos.CreateTodoCardArguments
import com.teamsparta.todo.todocards.dtos.TodoCardDto
import org.springframework.stereotype.Service

@Service
class TodoCardServiceImpl(
    val todoCardRepository: TodoCardRepository,
): TodoCardService {
    override fun createTodoCard(createTodoCardArguments: CreateTodoCardArguments): TodoCardDto {
        val result = todoCardRepository.save(createTodoCardArguments.to())

        return TodoCardDto.from(result)
    }
}
