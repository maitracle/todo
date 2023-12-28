package com.teamsparta.todo.todocards

import com.teamsparta.todo.todocards.dtos.CreateTodoCardArguments
import com.teamsparta.todo.todocards.dtos.TodoCardDto
import com.teamsparta.todo.todocards.dtos.UpdateTodoCardArguments
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
        val todoCard = todoCardService.createTodoCard(createTodoCardArguments)

        return ResponseEntity
            .status(201)
            .body(todoCard)
    }

    @GetMapping
    fun findAllTodoCard(): ResponseEntity<List<TodoCardDto>> {
        val todoCards = todoCardService.findAll()

        return ResponseEntity
            .status(200)
            .body(todoCards)
    }

    @GetMapping("/{todoCardId}")
    fun findTodoCard(
        @PathVariable todoCardId: Long,
    ): ResponseEntity<TodoCardDto?> {
        val todoCard = todoCardService.findById(todoCardId)

        return ResponseEntity
            .status(200)
            .body(todoCard)
    }

    @PutMapping("/{todoCardId}")
    fun updateTodoCard(
        @PathVariable todoCardId: Long,
        @RequestBody todoCardArguments: UpdateTodoCardArguments,
    ): ResponseEntity<TodoCardDto> {
        val arguments = UpdateTodoCardArguments(
            id = todoCardId,
            title = todoCardArguments.title,
            content = todoCardArguments.content,
            authorName = todoCardArguments.authorName,
        )

        val todoCard: TodoCardDto = todoCardService.updateTodoCard(arguments)

        return ResponseEntity
            .status(200)
            .body(todoCard)
    }
}
