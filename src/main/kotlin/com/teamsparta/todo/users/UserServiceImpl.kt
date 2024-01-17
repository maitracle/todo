package com.teamsparta.todo.users

import com.teamsparta.todo.users.dtos.CreateUserArguments
import com.teamsparta.todo.users.dtos.UserDto
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    val userRepository: UserRepository,
) : UserService {
    override fun createUser(createUserArguments: CreateUserArguments): UserDto {
        val createdUser = userRepository.save(createUserArguments.to())

        return UserDto.from(createdUser)
    }
}
