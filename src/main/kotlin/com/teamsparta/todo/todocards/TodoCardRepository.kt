package com.teamsparta.todo.todocards

import org.springframework.data.jpa.repository.JpaRepository

interface TodoCardRepository: JpaRepository<TodoCard, Long> {
    fun findAllByOrderByCreatedAtDesc(): List<TodoCard>
    fun findAllByOrderByCreatedAtAsc(): List<TodoCard>
    fun findAllByAuthorId(authorId: Long): List<TodoCard>
}
