package com.teamsparta.todo.replies

import com.teamsparta.todo.todocards.TodoCard
import jakarta.persistence.*

@Entity
class Reply(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column
    val content: String,
    @Column
    val authorName: String,
    @Column
    val password: String,
    @ManyToOne
    var todoCard: TodoCard,
)
