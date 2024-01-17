package com.teamsparta.todo.todocards

interface CustomTodoCardRepository {
    fun findAllByOrderByCreatedAtDesc(): List<TodoCard>
    fun findAllByOrderByCreatedAtAsc(): List<TodoCard>
    fun findAllByAuthorId(authorId: Long): List<TodoCard>
}
