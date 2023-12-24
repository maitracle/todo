package com.teamsparta.todo.todocards

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/todo-cards")
@RestController
class TodoCardController(
    val todoCardService: TodoCardService,
) {
    @PostMapping
    fun createTodoCard(): ResponseEntity<TodoCardDto> {
        val todoCard =  todoCardService.createTodoCard()

        return ResponseEntity
            .status(201)
            .body(todoCard)
    }
}
