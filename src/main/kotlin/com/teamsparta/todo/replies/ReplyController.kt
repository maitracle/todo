package com.teamsparta.todo.replies

import com.teamsparta.todo.replies.dtos.CreateReplyArguments
import com.teamsparta.todo.replies.dtos.DeleteReplyArguments
import com.teamsparta.todo.replies.dtos.ReplyDto
import com.teamsparta.todo.replies.dtos.UpdateReplyArguments
import com.teamsparta.todo.todocards.dtos.TodoCardDto
import com.teamsparta.todo.todocards.dtos.UpdateTodoCardArguments
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/replies")
@RestController
class ReplyController(
    val replyService: ReplyService,
) {
    @PostMapping
    fun createReply(
        @RequestBody createReplyArguments: CreateReplyArguments,
    ): ResponseEntity<ReplyDto> {
        val result = replyService.createReply(createReplyArguments)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(result)
    }

    @PutMapping("/{replyId}")
    fun updateReply(
        @PathVariable replyId: Long,
        @RequestBody updateReplyArguments: UpdateReplyArguments,
    ): ResponseEntity<ReplyDto> {
        val arguments = UpdateReplyArguments(
            id = replyId,
            content = updateReplyArguments.content,
            password = updateReplyArguments.password,
        )
        val reply = replyService.updateReply(arguments)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(reply)
    }

    @DeleteMapping("/{replyId}")
    fun deleteReply(
        @PathVariable replyId: Long,
        @RequestBody deleteReplyArguments: DeleteReplyArguments,
    ): ResponseEntity<Unit> {
        val arguments = DeleteReplyArguments(
            id = replyId,
            password = deleteReplyArguments.password,
        )

        replyService.deleteReply(arguments)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(null)
    }
}
