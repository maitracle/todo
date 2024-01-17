package com.teamsparta.todo.users

import com.teamsparta.todo.securities.JwtPlugin
import com.teamsparta.todo.users.dtos.CreateUserArguments
import com.teamsparta.todo.users.dtos.SignInArguments
import com.teamsparta.todo.users.dtos.UserDto
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin,
) : UserService {
    override fun createUser(createUserArguments: CreateUserArguments): UserDto {
        val user = User(
            username = createUserArguments.username,
            password = passwordEncoder.encode(createUserArguments.password),
        )

        val createdUser = userRepository.save(user)

        val token = jwtPlugin.generateAccessToken(
            subject = user.id.toString(),
            username = user.username,
        )

        return UserDto.from(createdUser, token)
    }

    override fun signIn(signInArguments: SignInArguments): UserDto {
        val user = userRepository.findByUsername(signInArguments.username)
            ?: throw Exception("user is not found")

        if (user.username != signInArguments.username ||
            !passwordEncoder.matches(signInArguments.password, user.password)
        ) {
            throw Exception("authentication failed")
        }

        val token = jwtPlugin.generateAccessToken(
            subject = user.id.toString(),
            username = user.username,
        )

        return UserDto.from(user, token)
    }
}
