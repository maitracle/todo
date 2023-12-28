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

        return if (foundTodoCard != null) {
            TodoCardDto.from(foundTodoCard)
        } else {
            null
        }
    }

    override fun findAll(): List<TodoCardDto> {
        val foundTodoCards = todoCardRepository.findAll()

        val result = mutableListOf<TodoCardDto>()

        for (todoCard in foundTodoCards) {
            result.add(TodoCardDto.from(todoCard))
        }

        return result
    }

    override fun updateTodoCard(todoCardArguments: UpdateTodoCardArguments): TodoCardDto {
        val savedTodoCard = todoCardRepository.save(todoCardArguments.to())

        return TodoCardDto.from(savedTodoCard)
    }

    override fun deleteTodoCard(id: Long) {
        todoCardRepository.deleteById(id)
    }
}
