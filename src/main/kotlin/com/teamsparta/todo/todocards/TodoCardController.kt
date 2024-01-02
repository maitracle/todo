package com.teamsparta.todo.todocards

import com.teamsparta.todo.todocards.dtos.CreateTodoCardArguments
import com.teamsparta.todo.todocards.dtos.RetrieveTodoCardDto
import com.teamsparta.todo.todocards.dtos.TodoCardDto
import com.teamsparta.todo.todocards.dtos.UpdateTodoCardArguments
import org.springframework.http.HttpStatus
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
            .status(HttpStatus.CREATED)
            .body(todoCard)
    }

    @GetMapping
    fun findAllTodoCard(
        @RequestParam authorName: String?,
        @RequestParam sort: String?,
    ): ResponseEntity<List<TodoCardDto>> {
        val todoCards = todoCardService.findAll(authorName, sort)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoCards)
    }

    @GetMapping("/{todoCardId}")
    fun findTodoCard(
        @PathVariable todoCardId: Long,
    ): ResponseEntity<RetrieveTodoCardDto?> {
        val todoCard = todoCardService.findById(todoCardId)

        return ResponseEntity
            .status(HttpStatus.OK)
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
            .status(HttpStatus.OK)
            .body(todoCard)
    }

    @PatchMapping("/{todoCardId}/complete")
    fun completeTodoCard(
        @PathVariable todoCardId: Long,
    ): ResponseEntity<Unit> {
        todoCardService.completeTodoCard(todoCardId)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(null)
    }

    @DeleteMapping("/{todoCardId}")
    fun deleteTodoCard(
        @PathVariable todoCardId: Long,
    ): ResponseEntity<Unit> {
        todoCardService.deleteTodoCard(todoCardId)

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(null)
    }
}
