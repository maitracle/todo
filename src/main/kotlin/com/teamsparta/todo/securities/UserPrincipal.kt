package com.teamsparta.todo.securities

import com.teamsparta.todo.users.User

data class UserPrincipal(
    val id: Long,
    val username: String,
) {
    fun to(): User {
        return User(
            id = id,
            username = username,
            password = "",
        )
    }
}
