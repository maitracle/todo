package com.teamsparta.todo.todocards

import com.teamsparta.todo.replies.Reply
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.ZonedDateTime

@Entity
class TodoCard(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column
    val title: String,
    @Column
    val content: String,
    @Column
    val authorName: String,
    @OneToMany(mappedBy = "todoCard")
    val replies: List<Reply> = emptyList(),
) {
    @CreationTimestamp
    @Column(updatable = false)
    val createdAt: ZonedDateTime = ZonedDateTime.now()

    @Column(name = "is_completed")
    private var _isCompleted: Boolean = false

    val isCompleted: Boolean
        get() = _isCompleted

    fun complete() {
        _isCompleted = true
    }
}
