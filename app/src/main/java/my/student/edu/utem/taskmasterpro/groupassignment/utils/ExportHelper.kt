package my.student.edu.utem.taskmasterpro.groupassignment.utils

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class ExportHelper(private val context: Context) {

    fun exportTasksToJson(tasksJson: String): Boolean {
        return try {
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val fileName = "TaskMaster_Backup_$timeStamp.json"

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                saveUsingMediaStore(fileName, tasksJson)
            } else {
                saveToDownloadsLegacy(fileName, tasksJson)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    private fun saveUsingMediaStore(fileName: String, content: String): Boolean {
        return try {
            val resolver = context.contentResolver
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                put(MediaStore.MediaColumns.MIME_TYPE, "application/json")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    put(MediaStore.MediaColumns.RELATIVE_PATH, "${Environment.DIRECTORY_DOWNLOADS}/TaskMaster")
                }
            }

            // Use the correct MediaStore URI based on API level
            val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                // For Android 10+ (API 29+)
                resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
            } else {
                // For Android 9 and below (API 28 and below)
                resolver.insert(MediaStore.Files.getContentUri("external"), contentValues)
            }

            uri?.let {
                resolver.openOutputStream(it)?.use { outputStream ->
                    outputStream.write(content.toByteArray())
                }
                true
            } ?: false
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    @Suppress("DEPRECATION")
    private fun saveToDownloadsLegacy(fileName: String, content: String): Boolean {
        return try {
            val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val folder = File(downloadsDir, "TaskMaster")
            if (!folder.exists()) {
                folder.mkdirs()
            }

            val file = File(folder, fileName)
            file.writeText(content)
            file.exists()
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}