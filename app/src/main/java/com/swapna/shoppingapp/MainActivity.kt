package com.swapna.shoppingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.swapna.shoppingapp.settings.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.getValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val settingsViewModel by viewModels<SettingsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val startDestination by settingsViewModel.startDestination.collectAsState()

            FirebaseApp(startDestination)
        }
    }
}



