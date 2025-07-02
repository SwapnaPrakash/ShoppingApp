package com.swapna.shoppingapp.authentication.sign_in

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableIntStateOf
import androidx.hilt.navigation.compose.hiltViewModel
import com.swapna.shoppingapp.components.CompanyInfo
import com.swapna.shoppingapp.components.EmailAndPasswordContent
import com.swapna.shoppingapp.R
import com.swapna.shoppingapp.authentication.sign_up.AuthViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    onSignUpClick : () -> Unit,
    authViewModel: AuthViewModel = hiltViewModel()
){

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Sign In")
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
                modifier = Modifier.weight(1f).padding(12.dp),
                email = email,
                password = password,
                onEmailChange = { email = it },
                onPasswordChange = {password = it},
                onEmailClear = {email = ""},
                onPasswordClear = {password = ""},
                actionButtonContent = {
                    Text(text = "Sign In")
                },
                onActionButtonClick = {
                    authViewModel.signIn(email, password)
                }
            )

            SignUpBox(
                modifier = Modifier.weight(1f),
                onSignUpClick = onSignUpClick
            )
        }
    }
}

@Composable
fun SignUpBox(modifier: Modifier = Modifier,
              onSignUpClick : () -> Unit) {
    Box(
        modifier = modifier
        .fillMaxSize()
        .padding(bottom = 16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            modifier = Modifier.clickable{
                onSignUpClick()
            },
            text = "Sign up instead?",
            style = MaterialTheme.typography.titleMedium,
            textDecoration = TextDecoration.Underline,
            color = Color.Blue
        )
    }
}

@Composable
fun CustomTextField(modifier: Modifier = Modifier,
                    value: String,
                    onValueChange :(String)-> Unit,
                    placeholder : String,
                    onClear:()-> Unit,
                    isPasswordField: Boolean = false
) {
    var showPassword by rememberSaveable {
        mutableStateOf(false)
    }

    val passwordIconResource by remember (showPassword) {
        mutableIntStateOf(if(showPassword) R.drawable.ic_eye_filled else R.drawable.ic_eye_outline)
    }

    var visualTransformation by remember (showPassword) {
        mutableStateOf(if (isPasswordField && !showPassword) PasswordVisualTransformation() else VisualTransformation.None)
    }

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = {Text(placeholder)},
        shape = RoundedCornerShape(16.dp),
        visualTransformation = visualTransformation,
        trailingIcon = {
            AnimatedVisibility(
                visible = value.isNotEmpty(),
                enter = expandHorizontally(expandFrom = Alignment.Start),
                exit = shrinkHorizontally(shrinkTowards = Alignment.Start)
            ) {
                    if(isPasswordField){
                        IconButton(onClick =
                            {
                                showPassword = !showPassword
                            }
                        ){
                            Icon(
                                painter = painterResource(passwordIconResource),
                                contentDescription = "Show Password"
                            )
                        }
                    }else{
                        IconButton(onClick = onClear){
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear"
                            )
                        }
                    }
            }
        }
    )

}

