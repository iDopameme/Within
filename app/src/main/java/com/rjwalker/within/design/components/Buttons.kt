package com.rjwalker.within.design.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoginButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        enabled = true,
        modifier = Modifier.width(180.dp),
        content = {
            Text(text = "Login")
        }
    )
}

@Composable
fun SignUpButton(onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        enabled = true,
        modifier = Modifier.width(180.dp),
        content = {
            Text(text = "Sign Up")
        }
    )
}

@Preview(showBackground = true)
@Composable
fun LoginButtonPreview() {
    LoginButton(onClick = {})
}

@Preview(showBackground = true)
@Composable
fun SignUpButtonPreview() {
    SignUpButton(onClick = {})
}