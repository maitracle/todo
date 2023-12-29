package com.teamsparta.todo.replies

import com.teamsparta.todo.replies.dtos.CreateReplyArguments
import com.teamsparta.todo.replies.dtos.ReplyDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/replies")
@RestController
class ReplyController(
    val replyService: ReplyService,
) {
    @PostMapping
    fun writeReply(
        @RequestBody createReplyArguments: CreateReplyArguments,
    ): ResponseEntity<ReplyDto> {
        val result = replyService.createReply(createReplyArguments)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(result)
    }
}
