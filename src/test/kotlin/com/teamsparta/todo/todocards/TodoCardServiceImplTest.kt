package com.teamsparta.todo.todocards

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import org.springframework.data.repository.findByIdOrNull

class TodoCardServiceImplTest : BehaviorSpec({
    val todoCardRepository = mockk<TodoCardRepository>()
    val savedTodoCardId = 1L
    val notSavedTodoCardId = 10L
    val savedTodoCard = TodoCard(
        id = savedTodoCardId,
        title = "title",
        content = "content",
        authorName = "authorName",
        replies = emptyList(),
    )
    every { todoCardRepository.findByIdOrNull(savedTodoCardId) } returns savedTodoCard
    every { todoCardRepository.findByIdOrNull(notSavedTodoCardId) } returns null

    val service = TodoCardServiceImpl(
        todoCardRepository
    )

    Given("a saved todo card id") {
        val targetTodoCardId = savedTodoCardId

        When("execute findById") {
            val result = service.findById(targetTodoCardId)

            Then("result should not be null") {
                result shouldNotBe null
                result?.let {
                    it.id shouldBe savedTodoCard.id
                    it.title shouldBe savedTodoCard.title
                    it.content shouldBe savedTodoCard.content
                    it.authorName shouldBe savedTodoCard.authorName
                    it.replies shouldBe savedTodoCard.replies
                }
            }
        }
    }

    Given("a not saved todo card id") {
        val targetTodoCardId = notSavedTodoCardId

        When("execute findById") {
            val result = service.findById(targetTodoCardId)

            Then("result should be null") {
                result shouldBe null
            }
        }
    }
})
