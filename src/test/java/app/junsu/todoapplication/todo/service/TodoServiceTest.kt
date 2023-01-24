package app.junsu.todoapplication.todo.service

import app.junsu.todoapplication.todo.domain.Todo
import app.junsu.todoapplication.todo.domain.TodoRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
class TodoServiceTest {

    @MockkBean
    lateinit var repository: TodoRepository

    private lateinit var service: TodoService

    private val stub: Todo by lazy {
        Todo(
            id = 1,
            title = "test",
            description = "test description",
            done = false,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
        )
    }

    @BeforeEach
    private fun setUp() {
        service = TodoService(repository)
    }

    @Test
    fun `return one Todo`() {

        // Given
        every { repository.findByIdOrNull(1) } returns stub

        // When
        val actual = service.findById(1L)

        // Then
        assertThat(actual).isNotNull
        assertThat(actual).isEqualTo(stub)
    }
}
