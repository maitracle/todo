package com.teamsparta.todo.todocards

import com.teamsparta.todo.todocards.dtos.CreateTodoCardArguments
import com.teamsparta.todo.todocards.dtos.TodoCardDto
import com.teamsparta.todo.todocards.dtos.UpdateTodoCardArguments
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoCardServiceImpl(
    val todoCardRepository: TodoCardRepository,
): TodoCardService {
    override fun createTodoCard(createTodoCardArguments: CreateTodoCardArguments): TodoCardDto {
        val savedTodoCard = todoCardRepository.save(createTodoCardArguments.to())

        return TodoCardDto.from(savedTodoCard)
    }

    override fun findById(id: Long): TodoCardDto? {
        val foundTodoCard = todoCardRepository.findByIdOrNull(id)

        return foundTodoCard?.let { TodoCardDto.from(it) }
    }

    override fun findAll(): List<TodoCardDto> {
        val foundTodoCards = todoCardRepository.findAllByOrderByCreatedAtDesc()

        return foundTodoCards.map { TodoCardDto.from(it) }
    }

    override fun updateTodoCard(todoCardArguments: UpdateTodoCardArguments): TodoCardDto {
        val savedTodoCard = todoCardRepository.save(todoCardArguments.to())

        return TodoCardDto.from(savedTodoCard)
    }

    override fun deleteTodoCard(id: Long) {
        todoCardRepository.deleteById(id)
    }
}
