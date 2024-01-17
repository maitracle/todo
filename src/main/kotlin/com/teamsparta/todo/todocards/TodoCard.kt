package com.teamsparta.todo.todocards

import com.teamsparta.todo.replies.Reply
import com.teamsparta.todo.users.User
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
    @ManyToOne
    val author: User,
    @OneToMany(mappedBy = "todoCard")
    val replies: List<Reply> = emptyList(),
) {
    init {
        if (this.title.isEmpty() || this.title.length > 200) {
            throw TodoCardException("title must be at least 1 character and not more than 200 characters long")
        }

        if (this.content.isEmpty() || this.content.length > 1000) {
            throw TodoCardException("content must be at least 1 character and not more than 1000 characters long")
        }
    }

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

    fun checkAuthorization(requestUser: User) {
        if (requestUser.id != author.id) {
            throw Exception("not permitted")
        }
    }
}
