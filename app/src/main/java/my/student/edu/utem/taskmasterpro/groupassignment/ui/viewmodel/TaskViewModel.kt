package my.student.edu.utem.taskmasterpro.groupassignment.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import my.student.edu.utem.taskmasterpro.groupassignment.data.model.Task
import my.student.edu.utem.taskmasterpro.groupassignment.data.repository.ITaskRepository

class TaskViewModel(
    private val repository: ITaskRepository
) : ViewModel() {

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    fun loadTasks() {
        _tasks.value = repository.getTasks()
    }

    fun addTask(task: Task) {
        val updated = _tasks.value.orEmpty().toMutableList()
        updated.add(task)
        repository.saveTasks(updated)
        _tasks.value = updated
    }

    fun toggleTask(task: Task) {
        val updated = _tasks.value.orEmpty().map {
            if (it.id == task.id) it.copy(isCompleted = !it.isCompleted)
            else it
        }
        repository.saveTasks(updated)
        _tasks.value = updated
    }
}