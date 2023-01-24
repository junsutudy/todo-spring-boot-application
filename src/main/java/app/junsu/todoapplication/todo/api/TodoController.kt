package app.junsu.todoapplication.todo.api

import app.junsu.todoapplication.todo.api.model.TodoRequest
import app.junsu.todoapplication.todo.api.model.TodoResponse
import app.junsu.todoapplication.todo.service.TodoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.noContent
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/todos")
class TodoController(
    @Autowired private val todoService: TodoService,
) {

    @GetMapping
    fun getAll() = ok(todoService.findAll())

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Long) = ok(todoService.findById(id))

    @PostMapping
    fun create(@RequestBody request: TodoRequest) = ok(TodoResponse.of(todoService.create(request)))

    @PutMapping("/{id}")
    fun update(
        @PathVariable("id") id: Long,
        @RequestBody request: TodoRequest,
    ) = ok(
        TodoResponse.of(
            todoService.update(id, request)
        )
    )

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> = run {
        todoService.delete(id)
        noContent().build()
    }
}
