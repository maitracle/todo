package com.teamsparta.todo.todocards

import com.teamsparta.todo.securities.UserPrincipal
import com.teamsparta.todo.todocards.dtos.CreateTodoCardArguments
import com.teamsparta.todo.todocards.dtos.RetrieveTodoCardDto
import com.teamsparta.todo.todocards.dtos.TodoCardDto
import com.teamsparta.todo.todocards.dtos.UpdateTodoCardArguments
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/todo-cards")
@RestController
class TodoCardController(
    val todoCardService: TodoCardService,
) {
    @PostMapping
    fun createTodoCard(
        @RequestBody createTodoCardArguments: CreateTodoCardArguments,
        authentication: Authentication,
    ): ResponseEntity<TodoCardDto> {
        val userPrincipal = authentication.principal as UserPrincipal

        val todoCard = todoCardService.createTodoCard(createTodoCardArguments, userPrincipal.to())

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(todoCard)
    }

    @GetMapping
    fun findAllTodoCard(
        @RequestParam authorName: String?,
        @RequestParam sort: String?,
        authentication: Authentication,
    ): ResponseEntity<List<TodoCardDto>> {
        val userPrincipal = authentication.principal as UserPrincipal

        val todoCards = todoCardService.findAll(userPrincipal.id, sort)

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
        authentication: Authentication,
    ): ResponseEntity<TodoCardDto> {
        val userPrincipal = authentication.principal as UserPrincipal

        val arguments = UpdateTodoCardArguments(
            id = todoCardId,
            title = todoCardArguments.title,
            content = todoCardArguments.content,
        )

        val todoCard: TodoCardDto = todoCardService.updateTodoCard(arguments, userPrincipal.to())

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoCard)
    }

    @PatchMapping("/{todoCardId}/complete")
    fun completeTodoCard(
        @PathVariable todoCardId: Long,
        authentication: Authentication,
    ): ResponseEntity<TodoCardDto> {
        val userPrincipal = authentication.principal as UserPrincipal

        val result = todoCardService.completeTodoCard(todoCardId, userPrincipal.to())

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(result)
    }

    @DeleteMapping("/{todoCardId}")
    fun deleteTodoCard(
        @PathVariable todoCardId: Long,
        authentication: Authentication,
    ): ResponseEntity<Unit> {
        val userPrincipal = authentication.principal as UserPrincipal

        todoCardService.deleteTodoCard(todoCardId, userPrincipal.to())

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(null)
    }

    @ExceptionHandler
    fun handle(exception: TodoCardException?): ResponseEntity<String> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(exception?.message)
    }
}
