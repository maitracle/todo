package com.teamsparta.todo.todocards

import com.teamsparta.todo.todocards.dtos.CreateTodoCardArguments
import com.teamsparta.todo.todocards.dtos.RetrieveTodoCardDto
import com.teamsparta.todo.todocards.dtos.TodoCardDto
import com.teamsparta.todo.todocards.dtos.UpdateTodoCardArguments
import com.teamsparta.todo.users.User
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoCardServiceImpl(
    val todoCardRepository: TodoCardRepository,
): TodoCardService {
    override fun createTodoCard(createTodoCardArguments: CreateTodoCardArguments, author: User): TodoCardDto {
        val savedTodoCard = todoCardRepository.save(createTodoCardArguments.to(author))

        return TodoCardDto.from(savedTodoCard)
    }

    override fun findById(id: Long): RetrieveTodoCardDto? {
        val foundTodoCard = todoCardRepository.findByIdOrNull(id)

        return foundTodoCard?.let { RetrieveTodoCardDto.from(it) }
    }

    override fun findAll(authorId: Long?, sort: String?): List<TodoCardDto> {
        authorId?.let {
            return todoCardRepository.findAllByAuthorId(authorId)
                .map { TodoCardDto.from(it) }
        }

        return if (sort == "createdAt") {
            todoCardRepository.findAllByOrderByCreatedAtAsc()
        } else {
            todoCardRepository.findAllByOrderByCreatedAtDesc()
        }.map { TodoCardDto.from(it) }
    }

    override fun updateTodoCard(todoCardArguments: UpdateTodoCardArguments, user: User): TodoCardDto {
        todoCardArguments.id ?: throw Exception("todo card is not found")

        val foundTodoCard = todoCardRepository.findByIdOrNull(todoCardArguments.id)
            ?: throw Exception("todo card is not found")

        foundTodoCard.checkAuthorization(user)

        val savedTodoCard = todoCardRepository.save(todoCardArguments.to(user))

        return TodoCardDto.from(savedTodoCard)
    }

    override fun deleteTodoCard(id: Long, user: User) {
        val foundTodoCard = todoCardRepository.findByIdOrNull(id)
            ?: throw Exception("todo card is not found")

        foundTodoCard.checkAuthorization(user)

        todoCardRepository.deleteById(id)
    }

    override fun completeTodoCard(id: Long, user: User): TodoCardDto {
        val targetTodoCard = todoCardRepository.findByIdOrNull(id)
            ?: throw Exception("todo card is not found")

        targetTodoCard.complete()

        val result = todoCardRepository.save(targetTodoCard)

        return TodoCardDto.from(result)
    }
}
