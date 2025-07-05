package com.swapna.shoppingapp

import androidx.compose.runtime.Composable
import com.swapna.shoppingapp.navigation.FirebaseAppNavigation
import com.swapna.shoppingapp.navigation.NavigationDestination
import com.swapna.shoppingapp.ui.theme.ShoppingAppTheme

@Composable
fun FirebaseApp(startDestination: NavigationDestination) {
    ShoppingAppTheme {
        FirebaseAppNavigation(startDestination = startDestination)
    }
}

