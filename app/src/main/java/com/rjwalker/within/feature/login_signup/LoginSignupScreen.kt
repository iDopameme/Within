package com.rjwalker.within.feature.login_signup

import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rjwalker.within.R
import com.rjwalker.within.network.result.Result


private const val TAG = "LoginSignUpRoute"

@Composable
internal fun LoginSignUpRoute(
    modifier: Modifier = Modifier,
    viewModel: LoginSignupViewModel = hiltViewModel(),
) {
    val authResult by viewModel.authResult.collectAsState()

    LoginSignUpScreen(
        onLogin = viewModel::login,
        onSignup = viewModel::signup,
        authResult = authResult,
        modifier = modifier
    )
}


@Composable
fun LoginSignUpScreen(
    onLogin: (String, String) -> Unit,
    onSignup: (String, String) -> Unit,
    authResult: Result<Unit>,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    var isLogin by remember { mutableStateOf(true) }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    when (authResult) {
        is Result.Success -> {
            isLoading = false
            Log.d(TAG, "LoginSignUpScreen: Success")
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
        is Result.Error -> {
            isLoading = false
            Toast.makeText(context, "ERROR! ${authResult.exception}", Toast.LENGTH_SHORT).show()
        }
        is Result.Loading -> {
            isLoading = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = if (isLogin) stringResource(R.string.login) else stringResource(R.string.signup) )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text(text = stringResource(id = R.string.email)) },
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text(text = stringResource(id = R.string.password)) },
            visualTransformation = PasswordVisualTransformation()
        )

        Button(onClick = {
            if (isLogin) onLogin(email.value, password.value) else onSignup(email.value, password.value)
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
    LoginSignUpScreen(
        onLogin = { _, _ -> },
        onSignup = { _, _ -> },
        authResult = Result.Success(Unit)
    )
}