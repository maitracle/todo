package com.teamsparta.todo.replies

import com.teamsparta.todo.replies.dtos.CreateReplyArguments
import com.teamsparta.todo.replies.dtos.DeleteReplyArguments
import com.teamsparta.todo.replies.dtos.ReplyDto
import com.teamsparta.todo.replies.dtos.UpdateReplyArguments
import com.teamsparta.todo.securities.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/replies")
@RestController
class ReplyController(
    val replyService: ReplyService,
) {
    @PostMapping
    fun createReply(
        @RequestBody createReplyArguments: CreateReplyArguments,
        authentication: Authentication,
    ): ResponseEntity<ReplyDto> {
        val userPrincipal = authentication.principal as UserPrincipal

        val result = replyService.createReply(createReplyArguments, userPrincipal.to())

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(result)
    }

    @PutMapping("/{replyId}")
    fun updateReply(
        @PathVariable replyId: Long,
        @RequestBody updateReplyArguments: UpdateReplyArguments,
        authentication: Authentication,
    ): ResponseEntity<ReplyDto> {
        val userPrincipal = authentication.principal as UserPrincipal

        val arguments = UpdateReplyArguments(
            id = replyId,
            content = updateReplyArguments.content,
        )

        val reply = replyService.updateReply(arguments, userPrincipal.to())

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(reply)
    }

    @DeleteMapping("/{replyId}")
    fun deleteReply(
        @PathVariable replyId: Long,
        @RequestBody deleteReplyArguments: DeleteReplyArguments,
        authentication: Authentication,
    ): ResponseEntity<Unit> {
        val userPrincipal = authentication.principal as UserPrincipal

        val arguments = DeleteReplyArguments(
            id = replyId,
        )

        replyService.deleteReply(arguments, userPrincipal.to())

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(null)
    }
}
