package com.teamsparta.todo.users.dtos

import com.teamsparta.todo.users.User

data class UserDto(
    val id: Long?,
    val username: String,
    val token: String? = null,
) {
    companion object {
        fun from(user: User, token: String?): UserDto {
            return UserDto(
                id = user.id,
                username = user.username,
                token = token,
            )
        }
    }
}
