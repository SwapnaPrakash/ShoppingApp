package com.swapna.shoppingapp.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swapna.shoppingapp.datastore.DatastoreRepositoryImpl
import com.swapna.shoppingapp.navigation.NavigationDestination
import com.swapna.shoppingapp.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val datastoreRepositoryImpl: DatastoreRepositoryImpl
): ViewModel(){

    private val _startDestination = MutableStateFlow<NavigationDestination>(NavigationDestination.SignIn)
    val startDestination : StateFlow<NavigationDestination> = _startDestination

    init {
        viewModelScope.launch {
            delay(1000)
            datastoreRepositoryImpl.isUserAuthenticated.collect { authenticated ->
                _startDestination.value = if (authenticated) {
                    NavigationDestination.Home
                } else {
                    NavigationDestination.SignIn
                }
            }
        }
    }

    val authenticated = datastoreRepositoryImpl.isUserAuthenticated.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = false
    )

    fun saveIsAuthenticated(authenticated : Boolean){
        viewModelScope.launch {
            datastoreRepositoryImpl.saveUserAuthenticated(authenticated)
        }
    }

    fun clearAuthenticated(){
        viewModelScope.launch {
            authRepository.signOut()
            datastoreRepositoryImpl.clearIsUserAuthenticated()
        }
    }
}