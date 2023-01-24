package app.junsu.todoapplication.todo.api.model

import app.junsu.todoapplication.todo.domain.Todo
import java.time.LocalDateTime

data class TodoResponse(
    private val id: Long,
    private val title: String,
    private val description: String,
    private val done: Boolean,
    private val createdAt: LocalDateTime,
    private val updatedAt: LocalDateTime?,
) {
    companion object {
        fun of(todo: Todo?): TodoResponse {
            checkNotNull(todo) { "Todo is null." }

            return TodoResponse(
                id = todo.id!!,
                title = todo.title,
                description = todo.description,
                done = todo.done,
                createdAt = todo.createdAt,
                updatedAt = todo.updatedAt,
            )
        }
    }
}
