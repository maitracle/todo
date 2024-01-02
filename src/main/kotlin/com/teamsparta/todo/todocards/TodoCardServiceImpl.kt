package com.teamsparta.todo.todocards

import com.teamsparta.todo.todocards.dtos.CreateTodoCardArguments
import com.teamsparta.todo.todocards.dtos.RetrieveTodoCardDto
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

    override fun findById(id: Long): RetrieveTodoCardDto? {
        val foundTodoCard = todoCardRepository.findByIdOrNull(id)

        return foundTodoCard?.let { RetrieveTodoCardDto.from(it) }
    }

    override fun findAll(authorName: String?, sort: String?): List<TodoCardDto> {
        authorName?.let {
            return todoCardRepository.findAllByAuthorName(authorName)
                .map { TodoCardDto.from(it) }
        }

        return if (sort == "createdAt") {
            todoCardRepository.findAllByOrderByCreatedAtAsc()
        } else {
            todoCardRepository.findAllByOrderByCreatedAtDesc()
        }.map { TodoCardDto.from(it) }
    }

    override fun updateTodoCard(todoCardArguments: UpdateTodoCardArguments): TodoCardDto {
        val savedTodoCard = todoCardRepository.save(todoCardArguments.to())

        return TodoCardDto.from(savedTodoCard)
    }

    override fun deleteTodoCard(id: Long) {
        todoCardRepository.deleteById(id)
    }

    override fun completeTodoCard(id: Long) {
        val targetTodoCard = todoCardRepository.findByIdOrNull(id)

        targetTodoCard?.let {
            it.complete()
            todoCardRepository.save(it)
        }
    }
}
