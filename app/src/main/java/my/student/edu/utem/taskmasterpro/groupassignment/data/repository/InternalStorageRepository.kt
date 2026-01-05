package my.student.edu.utem.taskmasterpro.groupassignment.data.repository

import android.content.Context
import my.student.edu.utem.taskmasterpro.groupassignment.data.model.Task
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class InternalStorageRepository(
    private val context: Context
) : ITaskRepository {

    private val fileName = "tasks.json"
    private val gson = Gson()

    override fun getTasks(): List<Task> {
        return try {
            val file = File(context.filesDir, fileName)
            if (!file.exists()) return emptyList()
            val json = file.readText()
            val type = object : TypeToken<List<Task>>() {}.type
            gson.fromJson(json, type) ?: emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override fun saveTasks(tasks: List<Task>) {
        try {
            val file = File(context.filesDir, fileName)
            val json = gson.toJson(tasks)
            file.writeText(json)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}