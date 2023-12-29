package com.teamsparta.todo.replies

import com.teamsparta.todo.replies.dtos.CreateReplyArguments
import com.teamsparta.todo.replies.dtos.DeleteReplyArguments
import com.teamsparta.todo.replies.dtos.ReplyDto
import com.teamsparta.todo.replies.dtos.UpdateReplyArguments
import com.teamsparta.todo.todocards.TodoCardRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import kotlin.Exception

@Service
class ReplyServiceImpl(
    val replyRepository: ReplyRepository,
    val todoCardRepository: TodoCardRepository,
): ReplyService {
    override fun createReply(createReplyArguments: CreateReplyArguments): ReplyDto {
        val targetTodoCard = todoCardRepository.findByIdOrNull(createReplyArguments.todoCardId)
            ?: throw Exception("target todo card is not found")

        val reply = Reply(
            content = createReplyArguments.content,
            authorName = createReplyArguments.authorName,
            password = createReplyArguments.password,
            todoCard = targetTodoCard,
        )

        val result = replyRepository.save(reply)

        return ReplyDto.from(result)
    }

    override fun updateReply(updateReplyArguments: UpdateReplyArguments): ReplyDto {
        val foundReply = updateReplyArguments.id?.let {
            replyRepository.findByIdOrNull(it)
        } ?: throw Exception("target reply is not found")

        foundReply.changeContent(updateReplyArguments.content)

        replyRepository.save(foundReply)

        return ReplyDto.from(foundReply)
    }

    override fun deleteReply(deleteReplyArguments: DeleteReplyArguments) {
        deleteReplyArguments.id?.let {
            replyRepository.deleteById(it)
        }
    }
}
