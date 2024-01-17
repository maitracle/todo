package com.teamsparta.todo.users

import com.teamsparta.todo.users.dtos.CreateUserArguments
import com.teamsparta.todo.users.dtos.UserDto
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder, // 주입!
) : UserService {
    override fun createUser(createUserArguments: CreateUserArguments): UserDto {
        val user = User(
            username = createUserArguments.username,
            password = passwordEncoder.encode(createUserArguments.password),
        )

        val createdUser = userRepository.save(user)

        return UserDto.from(createdUser)
    }
}
