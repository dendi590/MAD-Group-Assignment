package my.student.edu.utem.taskmasterpro.groupassignment.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import my.student.edu.utem.taskmasterpro.groupassignment.data.model.Task
import my.student.edu.utem.taskmasterpro.groupassignment.data.repository.ExportRepository

class ExportViewModel(private val exportRepository: ExportRepository) : ViewModel() {

    private val _exportResult = MutableLiveData<Boolean>()
    val exportResult: LiveData<Boolean> get() = _exportResult

    fun backupTasks(tasks: List<Task>) {
        viewModelScope.launch(Dispatchers.IO) {
            val success = exportRepository.exportTasks(tasks)
            _exportResult.postValue(success)
        }
    }
}
