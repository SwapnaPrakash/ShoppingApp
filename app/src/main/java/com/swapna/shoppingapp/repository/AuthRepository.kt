package com.swapna.shoppingapp.repository

interface AuthRepository {
    fun signUp(
        email: String,
        password: String,
        onSignUpSuccess: () -> Unit,
        onSignUpFailure: (Exception) -> Unit
    )
    fun signIn(email: String, password: String)
}