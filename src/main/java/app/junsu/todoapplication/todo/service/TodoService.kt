package app.junsu.todoapplication.todo.service

import app.junsu.todoapplication.todo.api.model.TodoRequest
import app.junsu.todoapplication.todo.domain.Todo
import app.junsu.todoapplication.todo.domain.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort.Direction
import org.springframework.data.domain.Sort.by
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

@Service
class TodoService(
    @Autowired private val todoRepository: TodoRepository,
) {
    @Transactional(readOnly = true)
    fun findAll(): List<Todo> = todoRepository.findAll(
        by(
            Direction.ASC,
            "id",
        ),
    )

    @Transactional(readOnly = true)
    fun findById(id: Long): Todo =
        todoRepository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    @Transactional
    fun create(request: TodoRequest?): Todo {
        checkNotNull(request) { "TodoRequest is null" }

        return todoRepository.save(
            Todo(
                title = request.title,
                description = request.description,
                done = request.done,
                createdAt = LocalDateTime.now(),
            )
        )
    }

    @Transactional
    fun update(id: Long, request: TodoRequest?): Todo {
        checkNotNull(request) { "TodoRequest is null" }
        return findById(id).let {
            it.update(
                request.title,
                request.description,
                request.done,
            )
            todoRepository.save(it)
        }
    }

    @Transactional
    fun delete(id: Long) = todoRepository.deleteById(id)
}
