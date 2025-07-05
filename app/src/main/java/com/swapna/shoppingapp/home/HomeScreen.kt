package com.swapna.shoppingapp.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.swapna.shoppingapp.R
import com.swapna.shoppingapp.settings.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(R.string.home))
            },
                actions = {
                    IconButton(onClick = {
                        settingsViewModel.clearAuthenticated()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_logout),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(R.string.home))
        }
    }
}