package com.rjwalker.within.design.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rjwalker.within.R
import com.rjwalker.within.design.theme.onPrimaryLight
import com.rjwalker.within.design.theme.primaryLight

@Composable
fun WithinSection(
    text: String,
    icon: Painter,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .height(100.dp)
    ) {
        Image(
            modifier = Modifier
                .height(80.dp),
            painter = icon,
            contentDescription = null
        )
        Text(
            text = text,
            color = onPrimaryLight,
            modifier = Modifier
                .height(20.dp)
        )
    }
}

@Composable
fun WithinBottomAppBar(
    screens: List<@Composable () -> Unit>
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(primaryLight)
    ) {
        screens.forEach { screen ->
            screen()
        }
    }
}

@Preview
@Composable
fun WithinSectionPreview() {
    WithinSection(text = "Mail", icon = painterResource(id = R.drawable.mailbox_icon))
}

@Preview
@Composable
fun WithinBottomAppBarPreview() {
    val sections = listOf<@Composable () -> Unit>(
        { WithinSection(text = "Mail", icon = painterResource(id = R.drawable.mailbox_icon)) },
        { WithinSection(text = "Mail", icon = painterResource(id = R.drawable.mailbox_icon)) },
        { WithinSection(text = "Mail", icon = painterResource(id = R.drawable.mailbox_icon)) }
    )

    WithinBottomAppBar(screens = sections)
}