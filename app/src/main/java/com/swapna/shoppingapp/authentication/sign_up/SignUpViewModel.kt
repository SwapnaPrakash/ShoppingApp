package com.swapna.shoppingapp.authentication.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swapna.shoppingapp.base.AuthState
import com.swapna.shoppingapp.datastore.DatastoreRepositoryImpl
import com.swapna.shoppingapp.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val datastoreRepositoryImpl: DatastoreRepositoryImpl
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Initial)

    var authState : StateFlow<AuthState> = _authState.asStateFlow()

    fun signUp(email: String, password: String){
        _authState.value = AuthState.Loading

        viewModelScope.launch(Dispatchers.IO) {

            _authState.value = AuthState.Loading
            delay(1_000)

            repository.signUp(
                email = email,
                password = password,
                onSignUpSuccess = {
                    saveIsAuthenticated(true)
                    _authState.value = AuthState.Success
                },
                onSignUpFailure = { exception->
                    _authState.value = AuthState.Error(exception.message ?: "Unknown error")
                }
            )
        }
    }
    fun signIn(email: String, password: String){
        viewModelScope.launch(Dispatchers.IO) {

            _authState.value = AuthState.Loading
            delay(1_000)

            repository.signIn(email,password,
                onSignUpSuccess = {
                   // saveIsAuthenticated(true)
                    _authState.value = AuthState.Success
                },
                onSignUpFailure = { exception->
                    _authState.value = AuthState.Error(exception.message ?: "Unknown error")
                }
            )
        }
    }

    fun saveIsAuthenticated(authenticated : Boolean){
        viewModelScope.launch {
            datastoreRepositoryImpl.saveUserAuthenticated(authenticated)
        }
    }

}