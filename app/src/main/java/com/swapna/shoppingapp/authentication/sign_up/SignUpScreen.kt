package com.swapna.shoppingapp.authentication.sign_up

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.swapna.shoppingapp.R
import com.swapna.shoppingapp.base.AuthState
import com.swapna.shoppingapp.components.CompanyInfo
import com.swapna.shoppingapp.components.EmailAndPasswordContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    onBack: () -> Unit,
    authViewModel: AuthViewModel = hiltViewModel(),
    onSignUpSuccess:()-> Unit
) {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val authState by authViewModel.authState.collectAsState()

    LaunchedEffect(authState) {
        if(authState is AuthState.Success){
            onSignUpSuccess()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.sign_up)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                        )
                    }
                }
            )
        }
    ) { innerPadding->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            CompanyInfo(
                modifier = Modifier.weight(1f)
            )

            EmailAndPasswordContent(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp),
                email = email,
                password = password,
                onEmailChange = { email = it },
                onPasswordChange = { password = it },
                onEmailClear = { email = "" },
                onPasswordClear = { password = "" },
                onActionButtonClick = {
                    authViewModel.signUp(email, password)
                },
                actionButtonContent = {
                    if(authState is AuthState.Loading){
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp)
                        )
                    }else{
                        Text(text = stringResource(R.string.sign_up))
                    }
                },
                enableActionButton = authState !is AuthState.Loading
            )

            Box(modifier = Modifier.weight(1f)) {
                if(authState is AuthState.Error){
                    Text(text = (authState as AuthState.Error).message,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

        }
    }
}