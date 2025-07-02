package com.swapna.shoppingapp.components

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.navigation.NavBackStackEntry
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.tween
import com.swapna.shoppingapp.utils.Constants

fun AnimatedContentTransitionScope<NavBackStackEntry>.slideIntoContainerAnimation() = slideIntoContainer(
animationSpec = tween(
durationMillis = Constants.NAVIGATION_ANIMATION_DURATION,
easing = EaseIn
),
towards = AnimatedContentTransitionScope.SlideDirection.End
)

fun AnimatedContentTransitionScope<NavBackStackEntry>.slideOutOfContainerAnimation() = slideOutOfContainer(
    animationSpec = tween(
        durationMillis = Constants.NAVIGATION_ANIMATION_DURATION,
        easing = EaseIn
    ),
    towards = AnimatedContentTransitionScope.SlideDirection.Start
)