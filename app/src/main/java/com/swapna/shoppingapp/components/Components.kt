package com.swapna.shoppingapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.swapna.shoppingapp.R
import com.swapna.shoppingapp.authentication.sign_in.CustomTextField
import com.swapna.shoppingapp.ui.VerticalSpacer

@Composable
fun CompanyInfo(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(R.string.shopping_app),
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Composable
fun EmailAndPasswordContent(
    modifier: Modifier = Modifier,
    email : String,
    password : String,
    onEmailChange : (String)->Unit,
    onPasswordChange : (String) -> Unit,
    onEmailClear:()-> Unit,
    onPasswordClear:()-> Unit,
    actionButtonContent : @Composable () -> Unit,
    enableActionButton: Boolean = true,
    onActionButtonClick:()->Unit){

    Column(modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CustomTextField(
            modifier = modifier.fillMaxWidth(),
            value = email,
            onValueChange = onEmailChange,
            placeholder = stringResource(R.string.enter_your_email),
            onClear = onEmailClear
        )

        VerticalSpacer(8.dp)

        CustomTextField(
            modifier = modifier.fillMaxWidth(),
            value = password,
            onValueChange = onPasswordChange,
            placeholder = stringResource(R.string.enter_the_password),
            isPasswordField = true,
            onClear = onPasswordClear
        )

        VerticalSpacer(16.dp)

        Button(onClick = onActionButtonClick,
            enabled = enableActionButton) {
            actionButtonContent()
        }
    }
}