package my.student.edu.utem.taskmasterpro.groupassignment.data.repository

import android.content.Context
import com.google.gson.Gson
import my.student.edu.utem.taskmasterpro.groupassignment.data.model.Task
import my.student.edu.utem.taskmasterpro.groupassignment.utils.ExportHelper

class ExportRepository(private val context: Context) {
    private val exportHelper = ExportHelper(context)
    private val gson = Gson()

    fun exportTasks(tasks: List<Task>): Boolean {
        val json = gson.toJson(tasks)
        return exportHelper.exportTasksToJson(json)
    }
}