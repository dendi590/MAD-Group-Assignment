package my.edu.utem.settingsactivity.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import my.edu.utem.settingsactivity.repository.SettingsRepository
import kotlinx.coroutines.launch

class SettingsViewModel(private val settingsRepository: SettingsRepository) : ViewModel() {

    val settings = settingsRepository.settingsFlow.asLiveData()

    fun updateDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.updateDarkMode(enabled)
        }
    }
}