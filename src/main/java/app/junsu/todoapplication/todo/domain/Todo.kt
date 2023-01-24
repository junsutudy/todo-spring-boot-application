package app.junsu.todoapplication.todo.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "todos")
data class Todo(
    @Id @GeneratedValue val id: Long? = 0,
    @Column(name = "title") var title: String,
    @Lob @Column(name = "description") var description: String,
    @Column(name = "done") var done: Boolean,
    @Column(name = "created_at") var createdAt: LocalDateTime,
    @Column(name = "updated_at") var updatedAt: LocalDateTime? = null,
) {
    fun update(
        title: String,
        description: String,
        done: Boolean,
    ) {
        this.title = title
        this.description = description
        this.done = done
    }
}
