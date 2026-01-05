package my.student.edu.utem.taskmasterpro.groupassignment.data.repository

import my.student.edu.utem.taskmasterpro.groupassignment.data.model.Task

interface ITaskRepository {
    fun getTasks(): List<Task>
    fun saveTasks(tasks: List<Task>)
}