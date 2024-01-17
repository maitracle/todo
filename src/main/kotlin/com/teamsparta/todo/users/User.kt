package com.teamsparta.todo.users

import jakarta.persistence.*

@Entity(name = "todo_user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column
    val username: String,
    @Column
    val password: String,
)
