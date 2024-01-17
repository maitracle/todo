package com.teamsparta.todo.users.dtos

import com.teamsparta.todo.users.User

data class UserDto(
    val id: Long?,
    val username: String,
) {
    companion object {
        fun from(user: User): UserDto {
            return UserDto(
                id = user.id,
                username = user.username,
            )
        }
    }
}
