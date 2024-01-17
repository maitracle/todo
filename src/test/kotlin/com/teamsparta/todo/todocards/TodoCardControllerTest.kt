package com.teamsparta.todo.todocards

import com.teamsparta.todo.todocards.dtos.RetrieveTodoCardDto
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.springframework.http.HttpStatus
import java.time.ZonedDateTime

class TodoCardControllerTest : BehaviorSpec({
    val savedTodoCardId = 1L
    val notSavedTodoCardId = 10L
    val todoCardService = mockk<TodoCardService>()
    every { todoCardService.findById(savedTodoCardId) } returns RetrieveTodoCardDto(
        id = savedTodoCardId,
        title = "title",
        content = "content",
        authorName = "testUser",
        isCompleted = false,
        createdAt = ZonedDateTime.now(),
        replies = emptyList(),
    )
    every { todoCardService.findById(notSavedTodoCardId) } returns null

    val todoCardController = TodoCardController(todoCardService)

    Given("a saved todo card id") {
        val targetTodoCardId = savedTodoCardId

        When("execute findTodoCard") {
            val result = todoCardController.findTodoCard(targetTodoCardId)

            Then("status code should be ok") {
                result.statusCode shouldBe HttpStatus.OK
                result.body?.id shouldBe savedTodoCardId
            }
        }
    }

    Given("a not saved todo card id") {
        val targetTodoCardId = notSavedTodoCardId

        When("execute findById") {
            val result = todoCardController.findTodoCard(targetTodoCardId)

            Then("status code should be not found") {
                result.statusCode shouldBe HttpStatus.NOT_FOUND
                result.body shouldBe null
            }
        }
    }
})
