package com.teamsparta.todo.todocards

import org.springframework.stereotype.Service

@Service
class TodoCardServiceImpl(
    val todoCardRepository: TodoCardRepository,
): TodoCardService {
    override fun createTodoCard() {
        val mockTodoCard = TodoCard(
            title = "test title",
            content = "test content",
            authorName = "test author name",
        )

        todoCardRepository.save(mockTodoCard)
    }
}
