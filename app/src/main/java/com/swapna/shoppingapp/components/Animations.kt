package com.swapna.shoppingapp.components

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.navigation.NavBackStackEntry
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.tween
import com.swapna.shoppingapp.utils.Constants

fun AnimatedContentTransitionScope<NavBackStackEntry>.slideIntoContainerAnimation(
    towards: SlideDirection = AnimatedContentTransitionScope.SlideDirection.End
) = slideIntoContainer(
animationSpec = tween(
durationMillis = Constants.NAVIGATION_ANIMATION_DURATION,
easing = EaseIn
),
towards = towards
)

fun AnimatedContentTransitionScope<NavBackStackEntry>.slideOutOfContainerAnimation(
    towards: SlideDirection = AnimatedContentTransitionScope.SlideDirection.Start
) = slideOutOfContainer(
    animationSpec = tween(
        durationMillis = Constants.NAVIGATION_ANIMATION_DURATION,
        easing = EaseIn
    ),
    towards = towards
)