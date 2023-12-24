package com.teamsparta.todo.todocards

import com.teamsparta.todo.todocards.dtos.CreateTodoCardArguments
import com.teamsparta.todo.todocards.dtos.TodoCardDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/todo-cards")
@RestController
class TodoCardController(
    val todoCardService: TodoCardService,
) {
    @PostMapping
    fun createTodoCard(
        @RequestBody createTodoCardArguments: CreateTodoCardArguments,
    ): ResponseEntity<TodoCardDto> {
        val todoCard =  todoCardService.createTodoCard(createTodoCardArguments)

        return ResponseEntity
            .status(201)
            .body(todoCard)
    }
}
