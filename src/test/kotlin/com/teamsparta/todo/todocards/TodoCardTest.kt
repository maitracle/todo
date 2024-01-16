package com.teamsparta.todo.todocards

import com.teamsparta.todo.replies.Reply
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class TodoCardTest : BehaviorSpec({
    Given("information of todo card") {
        val id = null
        val title = "title"
        val content = "content"
        val authorName = "authorName"
        val replies = emptyList<Reply>()

        When("excute TodoCard constructor") {
            val result = TodoCard(
                id = id,
                title = title,
                content = content,
                authorName = authorName,
                replies = replies,
            )

            Then("result should be expected") {
                result.id shouldBe id
                result.title shouldBe title
                result.content shouldBe content
                result.authorName shouldBe authorName
                result.replies shouldBe replies
            }
        }
    }
})
