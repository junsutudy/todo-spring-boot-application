package app.junsu.todoapplication.todo.api.model

import app.junsu.todoapplication.todo.domain.Todo
import com.fasterxml.jackson.annotation.JsonIgnore

data class TodoListResponse(
    private val items: List<TodoResponse>,
) {

    val size
        @JsonIgnore get() = items.size

    fun get(index: Int) = items[index]

    companion object {
        fun of(items: List<Todo>) = TodoListResponse(items.map(TodoResponse::of))
    }
}
