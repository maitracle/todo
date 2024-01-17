package com.teamsparta.todo.todocards

import com.teamsparta.todo.QueryDslSupport
import com.teamsparta.todo.replies.QReply
import com.teamsparta.todo.users.QUser
import org.springframework.stereotype.Repository

@Repository
class TodoCardRepositoryImpl: CustomTodoCardRepository, QueryDslSupport() {
    private val todoCard = QTodoCard.todoCard
    private val reply = QReply.reply
    private val user = QUser.user

    override fun findAllByOrderByCreatedAtDesc(): List<TodoCard> {
        return queryFactory.selectFrom(todoCard)
            .leftJoin(todoCard.replies, reply)
            .fetchJoin()
            .leftJoin(todoCard.author, user)
            .fetchJoin()
            .orderBy(todoCard.createdAt.desc())
            .fetch()
    }

    override fun findAllByOrderByCreatedAtAsc(): List<TodoCard> {
        return queryFactory.selectFrom(todoCard)
            .leftJoin(todoCard.replies, reply)
            .fetchJoin()
            .leftJoin(todoCard.author, user)
            .fetchJoin()
            .orderBy(todoCard.createdAt.asc())
            .fetch()
    }

    override fun findAllByAuthorId(authorId: Long): List<TodoCard> {
        return queryFactory.selectFrom(todoCard)
            .leftJoin(todoCard.replies, reply)
            .fetchJoin()
            .leftJoin(todoCard.author, user)
            .fetchJoin()
            .where(todoCard.author.id.eq(authorId))
            .fetch()
    }
}
