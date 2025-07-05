package com.swapna.shoppingapp.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.swapna.shoppingapp.authentication.sign_in.SignInScreen
import com.swapna.shoppingapp.authentication.sign_up.SignUpScreen
import com.swapna.shoppingapp.components.slideIntoContainerAnimation
import com.swapna.shoppingapp.components.slideOutOfContainerAnimation
import com.swapna.shoppingapp.home.HomeScreen
import com.swapna.shoppingapp.splash.SplashScreen
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection

@Composable
fun FirebaseAppNavigation(modifier: Modifier = Modifier,
                          startDestination: NavigationDestination) {

    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination.route
    ) {

        composable(
            route = NavigationDestination.SignIn.route,
            enterTransition = {
                slideIntoContainerAnimation()
            }, exitTransition = {
                slideOutOfContainerAnimation()
            }) {
            SignInScreen(
                onSignUpClick = {
                    navController.navigate(NavigationDestination.SignUp.route)
                }
            )
        }

        composable(route = NavigationDestination.SignUp.route ,
            enterTransition = {
                slideIntoContainerAnimation(
                    towards = SlideDirection.Left
                )
            }, exitTransition = {
                slideOutOfContainerAnimation(
                    towards = SlideDirection.Right
                )
            }) {
            SignUpScreen(
                onBack = {
                    navController.popBackStack()
                },
                onSignUpSuccess = {
                    navController.navigate(NavigationDestination.Home.route)
                }
            )
        }

        composable(
            route = NavigationDestination.Home.route,
            enterTransition = {
                slideIntoContainerAnimation(
                    towards = SlideDirection.Left
                )
            }, exitTransition = {
                slideOutOfContainerAnimation(
                    towards = SlideDirection.Right
                )
            }) {
            HomeScreen()
        }

        composable(
            route = NavigationDestination.Splash.route,) {
            SplashScreen()
        }


    }


}

