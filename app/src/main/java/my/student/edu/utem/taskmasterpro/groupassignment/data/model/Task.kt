package my.student.edu.utem.taskmasterpro.groupassignment.data.model

import java.util.UUID

data class Task(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val dueDate: String,
    val isCompleted: Boolean = false
)