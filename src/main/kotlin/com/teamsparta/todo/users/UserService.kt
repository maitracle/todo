package com.teamsparta.todo.users

import com.teamsparta.todo.users.dtos.CreateUserArguments
import com.teamsparta.todo.users.dtos.SignInArguments
import com.teamsparta.todo.users.dtos.UserDto

interface UserService {
    fun createUser(createUserArguments: CreateUserArguments): UserDto
    fun signIn(signInArguments: SignInArguments): UserDto
}
