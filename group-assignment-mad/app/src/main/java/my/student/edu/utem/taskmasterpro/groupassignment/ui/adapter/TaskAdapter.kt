package my.student.edu.utem.taskmasterpro.groupassignment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import my.student.edu.utem.taskmasterpro.databinding.ItemTaskBinding
import my.student.edu.utem.taskmasterpro.groupassignment.data.model.Task

class TaskAdapter(
    private var tasks: List<Task>,
    private val onChecked: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(val binding: ItemTaskBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.binding.apply {
            taskTitle.text = task.title
            taskDueDate.text = "Due: ${task.dueDate}"
            taskCheckbox.isChecked = task.isCompleted
            taskCheckbox.setOnCheckedChangeListener { _, _ ->
                onChecked(task)
            }
        }
    }

    override fun getItemCount() = tasks.size

    fun updateData(newTasks: List<Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }
}