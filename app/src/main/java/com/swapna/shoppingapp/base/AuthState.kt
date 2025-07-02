package com.swapna.shoppingapp.base

sealed interface AuthState {

    data object Initial : AuthState

    data object Loading : AuthState

    data object Success : AuthState

    data class Error(val message: String) : AuthState
}