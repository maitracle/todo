package com.teamsparta.todo.users.dtos

import com.teamsparta.todo.users.User

data class CreateUserArguments(
    val username: String,
    val password: String,
) {
    fun to(): User {
        return User(
            username = username,
            password = password,
        )
    }
}
