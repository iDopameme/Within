package com.rjwalker.within.feature.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rjwalker.within.R
import com.rjwalker.within.data.model.DarkThemeConfig
import com.rjwalker.within.design.theme.WithinTheme
import com.rjwalker.within.design.theme.WithinTypography

@Composable
fun SettingsDialog(
    onDismiss: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val settingsUiState by viewModel.settingsUiState.collectAsStateWithLifecycle()
    SettingsDialog(
        settingsUiState = settingsUiState,
        onChangeShowOnboarding = viewModel::updateShowOnboarding,
        onChangeDarkThemeConfig = viewModel::updateDarkThemeConfig,
        onDismiss = onDismiss
    )
}

@Composable
internal fun SettingsDialog(
    settingsUiState: SettingsUiState,
    onChangeShowOnboarding: (showOnboarding: Boolean) -> Unit,
    onChangeDarkThemeConfig: (darkThemeConfig: DarkThemeConfig) -> Unit,
    onDismiss: () -> Unit
) {
    val config = LocalConfiguration.current

    AlertDialog(
        properties = DialogProperties(),
        modifier = Modifier.widthIn(max = config.screenWidthDp.dp - 80.dp),
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = stringResource(id = R.string.settings),
                style = WithinTypography.titleLarge
            )
        },
        text = {
            HorizontalDivider()
            Column(Modifier.verticalScroll(rememberScrollState())) {
                when (settingsUiState) {
                    SettingsUiState.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(vertical = 24.dp)
                        )
                    }
                    is SettingsUiState.Success -> {
                        SettingsPanel(
                            settings = settingsUiState.settings,
                            onChangeShowOnboarding = onChangeShowOnboarding,
                            onChangeDarkThemeConfig = onChangeDarkThemeConfig
                        )
                    }
                }
                HorizontalDivider()
            }
        },
        confirmButton = {
            Text(
                stringResource(id = R.string.ok),
                style = WithinTypography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable { onDismiss() }
            )
        }
    )
}

@Composable
private fun ColumnScope.SettingsPanel(
    settings: UserSettings,
    onChangeShowOnboarding: (showOnboarding: Boolean) -> Unit,
    onChangeDarkThemeConfig: (darkThemeConfig: DarkThemeConfig) -> Unit
) {
    Column(modifier = Modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 12.dp)
        ) {
            Text(
                text = stringResource(id = R.string.show_onboarding),
                style = WithinTypography.titleMedium,
                modifier = Modifier.weight(1f)
            )
            Switch(
                checked = settings.showOnboarding,
                onCheckedChange = {
                    onChangeShowOnboarding(it)
                }
            )
        }
        Text(
            text = stringResource(R.string.dark_mode_settings),
            style = WithinTypography.titleMedium,
        )
        Column(Modifier
            .selectableGroup()) {
            SettingsDarkModeSelectionRow(
                text = stringResource(id = R.string.system_default),
                selected = settings.darkThemeConfig == DarkThemeConfig.FOLLOW_SYSTEM,
                onClick = { onChangeDarkThemeConfig(DarkThemeConfig.FOLLOW_SYSTEM) }
            )
            SettingsDarkModeSelectionRow(
                text = stringResource(id = R.string.light_mode),
                selected = settings.darkThemeConfig == DarkThemeConfig.LIGHT,
                onClick = { onChangeDarkThemeConfig(DarkThemeConfig.LIGHT) }
            )
            SettingsDarkModeSelectionRow(
                text = stringResource(id = R.string.dark_mode),
                selected = settings.darkThemeConfig == DarkThemeConfig.DARK,
                onClick = { onChangeDarkThemeConfig(DarkThemeConfig.DARK) }
            )
        }

    }
}

@Composable
private fun SettingsDarkModeSelectionRow(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                role = Role.RadioButton,
                onClick = onClick
            )
            .padding(vertical = 12.dp)
    ) {
        RadioButton(
            selected = selected,
            onClick = null
        )

        Text(
            text = text
        )
    }
}

@Preview
@Composable
private fun SettingsDialogPreview() {
    WithinTheme {
        SettingsDialog(
            settingsUiState = SettingsUiState.Success(
                settings = UserSettings(
                    showOnboarding = true,
                    darkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM
                )
            ),
            onChangeShowOnboarding = {},
            onChangeDarkThemeConfig = {},
            onDismiss = {}
        )
    }
}

@Preview
@Composable
private fun SettingsDialogLoadingPreview() {
    WithinTheme {
        SettingsDialog(
            settingsUiState = SettingsUiState.Loading,
            onChangeShowOnboarding = {},
            onChangeDarkThemeConfig = {},
            onDismiss = {}
        )
    }
}