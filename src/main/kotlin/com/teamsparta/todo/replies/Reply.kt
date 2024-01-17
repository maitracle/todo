package com.teamsparta.todo.replies

import com.teamsparta.todo.todocards.TodoCard
import com.teamsparta.todo.users.User
import jakarta.persistence.*

@Entity
class Reply(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column
    var content: String,
    @ManyToOne(fetch = FetchType.LAZY)
    val author: User,
    @ManyToOne(fetch = FetchType.LAZY)
    var todoCard: TodoCard,
) {
    fun changeContent(content: String) {
        this.content = content
    }

    fun checkAuthorization(user: User) {
        if (this.author.id != user.id) {
            throw Exception("no permission")
        }
    }
}
