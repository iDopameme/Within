package com.rjwalker.within.feature.login_signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rjwalker.within.R

@Composable
fun LoginSignUpScreen() {
    var isLogin by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Text(text = if (isLogin) stringResource(R.string.login) else stringResource(R.string.signup) )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = stringResource(id = R.string.email)) },
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = stringResource(id = R.string.password)) },
            visualTransformation = PasswordVisualTransformation()
        )

        Button(onClick = {
            // TODO: Perform login or signup logic here
        }) {
            Text(text = if (isLogin) stringResource(R.string.login) else stringResource(R.string.signup))
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = { isLogin = !isLogin }) {
            Text(text = if (isLogin) stringResource(R.string.needs_to_signup) else stringResource(R.string.already_have_account))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginSignUpScreenPreview() {
    LoginSignUpScreen()
}