package app.junsu.todoapplication.todo.domain

import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long> {
    fun findAllByDoneIsFalseOrderByDesc(): List<Todo>?
}