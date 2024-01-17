package com.teamsparta.todo.users

import com.teamsparta.todo.users.dtos.CreateUserArguments
import com.teamsparta.todo.users.dtos.UserDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1/users")
@RestController
class UserController(
    val userService: UserService,
) {
    @PostMapping
    fun createUser(
        @RequestBody createUserArguments: CreateUserArguments,
    ): ResponseEntity<UserDto> {
        val user = userService.createUser(createUserArguments)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(user)
    }
}
