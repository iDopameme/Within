package com.rjwalker.within.design.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rjwalker.within.R

@Composable
fun WelcomeComponent(isUserNew: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row {
            Text(
                text = stringResource(id = R.string.welcome_title),
                style = MaterialTheme.typography.headlineLarge,
            )
        }
        Row {
            Text(text = stringResource(id = R.string.welcome_body_1))
        }
        Row {
            Text(text = stringResource(id = R.string.welcome_body_2))
        }
    }
}

@ThemePreviews
@Composable
fun WelcomeComponentPreview() {
    WelcomeComponent(isUserNew = true)
}