package com.teamsparta.todo.todocards

import org.springframework.data.jpa.repository.JpaRepository

interface TodoCardRepository: JpaRepository<TodoCard, Long>, CustomTodoCardRepository
