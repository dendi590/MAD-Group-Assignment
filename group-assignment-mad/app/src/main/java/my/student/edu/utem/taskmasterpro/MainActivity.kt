package my.student.edu.utem.taskmasterpro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import my.student.edu.utem.taskmasterpro.databinding.ActivityMainBinding
import my.student.edu.utem.taskmasterpro.groupassignment.data.model.Task
import my.student.edu.utem.taskmasterpro.groupassignment.data.repository.InternalStorageRepository
import my.student.edu.utem.taskmasterpro.groupassignment.ui.adapter.TaskAdapter
import my.student.edu.utem.taskmasterpro.groupassignment.ui.viewmodel.TaskViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TaskViewModel
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = InternalStorageRepository(this)
        viewModel = TaskViewModel(repository)

        adapter = TaskAdapter(listOf()) { task ->
            viewModel.toggleTask(task)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Sample data for testing
        if (repository.getTasks().isEmpty()) {
            repository.saveTasks(
                listOf(
                    Task(title = "Sample Task 1", dueDate = "2026-01-10"),
                    Task(title = "Sample Task 2", dueDate = "2026-01-15")
                )
            )
        }

        viewModel.tasks.observe(this) { tasks ->
            adapter.updateData(tasks)
        }

        viewModel.loadTasks()
    }
}