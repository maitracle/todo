package com.teamsparta.todo.todocards

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
class TodoCard(
    @Column
    val title: String,
    @Column
    val content: String,
    @Column
    val authorName: String,
    @Column
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
