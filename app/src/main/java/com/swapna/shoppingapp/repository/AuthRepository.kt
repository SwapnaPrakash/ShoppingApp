package com.swapna.shoppingapp.repository

import com.swapna.shoppingapp.base.AuthState

interface AuthRepository {
    fun signUp(
        email: String,
        password: String,
        onSignUpSuccess: () -> Unit,
        onSignUpFailure: (Exception) -> Unit
    )
    fun signIn(email: String, password: String)
}