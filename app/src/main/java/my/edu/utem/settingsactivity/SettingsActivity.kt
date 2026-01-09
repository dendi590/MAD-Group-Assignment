package my.edu.utem.settingsactivity

import my.edu.utem.R
import android.os.Bundle
import com.google.android.material.switchmaterial.SwitchMaterial
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import my.edu.utem.settingsactivity.repository.SettingsRepository
import my.edu.utem.settingsactivity.viewmodel.SettingsViewModel
import my.edu.utem.settingsactivity.viewmodel.SettingsViewModelFactory

class SettingsActivity : AppCompatActivity() {

    private lateinit var switchDarkMode: SwitchMaterial
    private val viewModel: SettingsViewModel by viewModels {
        SettingsViewModelFactory(SettingsRepository(applicationContext))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        switchDarkMode = findViewById(R.id.switchDarkMode)

        setupToolbar()
        setupObservers()
        setupListeners()
    }

    private fun setupToolbar() {
        val toolbar = findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupObservers() {
        viewModel.settings.observe(this) { settings ->
            switchDarkMode.isChecked = settings.darkModeEnabled
            applyDarkMode(settings.darkModeEnabled)
        }
    }

    private fun setupListeners() {
        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateDarkMode(isChecked)
        }

        findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.btnBackup).setOnClickListener {
            android.widget.Toast.makeText(
                this,
                "Export feature coming soon!",
                android.widget.Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun applyDarkMode(enabled: Boolean) {
        val targetMode = if (enabled) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
        
        if (AppCompatDelegate.getDefaultNightMode() != targetMode) {
            AppCompatDelegate.setDefaultNightMode(targetMode)
        }
    }
}