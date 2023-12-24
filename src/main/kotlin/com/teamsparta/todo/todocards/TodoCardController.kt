package com.teamsparta.todo.todocards

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/todo-cards")
@RestController
class TodoCardController {
    @PostMapping
    fun createTodoCard(): ResponseEntity<Unit> {
        TODO("not implemented")
    }
}
