package com.teamsparta.todo.todocards

import com.teamsparta.todo.replies.Reply
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class TodoCardTest : BehaviorSpec({
    Given("information of todo card") {
        val id = null
        val title = "title"
        val content = "content"
        val authorName = "authorName"
        val replies = emptyList<Reply>()

        When("execute TodoCard constructor") {
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

    Given("information of todo card with empty title") {
        val id = null
        val title = ""
        val content = "content"
        val authorName = "authorName"
        val replies = emptyList<Reply>()

        When("execute TodoCard constructor") {
            val result = shouldThrow<TodoCardException> {
                TodoCard(
                    id = id,
                    title = title,
                    content = content,
                    authorName = authorName,
                    replies = replies,
                )
            }

            Then("thrown exception message should be expected") {
                result.message shouldBe "title must be at least 1 character and not more than 200 characters long"
            }
        }
    }

    Given("information of todo card with empty content") {
        val id = null
        val title = "title"
        val content = ""
        val authorName = "authorName"
        val replies = emptyList<Reply>()

        When("execute TodoCard constructor") {
            val result = shouldThrow<TodoCardException> {
                TodoCard(
                    id = id,
                    title = title,
                    content = content,
                    authorName = authorName,
                    replies = replies,
                )
            }

            Then("thrown exception message should be expected") {
                result.message shouldBe "content must be at least 1 character and not more than 1000 characters long"
            }
        }
    }
})
