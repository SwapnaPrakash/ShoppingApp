package com.swapna.shoppingapp.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth : FirebaseAuth
): AuthRepository {

    override fun signUp(
        email: String,
        password: String,
        onSignUpSuccess: () -> Unit,
        onSignUpFailure: (Exception) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener { authResult ->
                Log.d("TAG", "authResult:$auth")
                onSignUpSuccess()
            }
            .addOnFailureListener {exception->
                onSignUpFailure(exception)
            }
    }

    override fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password)
    }
}