package com.rjwalker.within.design.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rjwalker.within.R
import com.rjwalker.within.design.icons.WithinIcons

@Composable
fun HomeTabScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.todays_agenda),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )
            Icon(imageVector = WithinIcons.EnterArrow, contentDescription = null)
        }
    }
}

@Composable
fun AgendaItem(
    title: String,
    description: String,
    time: String,
    isCompleted: Boolean
) {
    var checked by remember { mutableStateOf(isCompleted) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = time,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )
            Checkbox(checked = checked, onCheckedChange = {checked = it} )
        }
        Row {
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1f)
            )
        }

    }
}



@ThemePreviews
@Composable
fun HomeTabScreenPreview() {
    HomeTabScreen()
}

@ThemePreviews
@Composable
fun AgendaItemPreview() {
    AgendaItem(
        title = "Sample Title",
        description = "Sample Description",
        time = "Sample Time",
        isCompleted = false
    )

}