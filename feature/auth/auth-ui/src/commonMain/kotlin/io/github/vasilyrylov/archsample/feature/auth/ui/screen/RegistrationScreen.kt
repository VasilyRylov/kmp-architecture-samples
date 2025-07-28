package io.github.vasilyrylov.archsample.feature.auth.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.vasilyrylov.archsample.common.ui.icons.ArrowBack
import io.github.vasilyrylov.archsample.common.ui.icons.Icons
import io.github.vasilyrylov.archsample.feature.auth.ui.element.RegistrationScreenContent
import io.github.vasilyrylov.archsample.feature.auth.ui.data.RegistrationViewState
import io.github.vasilyrylov.archsample.feature.auth.ui.element.dialog.ConfirmDialog
import io.github.vasilyrylov.archsample.resources.Res
import io.github.vasilyrylov.archsample.resources.confirm_registration_dialog_description
import io.github.vasilyrylov.archsample.resources.confirm_registration_dialog_title
import io.github.vasilyrylov.archsample.resources.input_password
import io.github.vasilyrylov.archsample.resources.registration
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    viewState: RegistrationViewState,
    onBackClick: () -> Unit,
    handleChangeRegistrationData: (name: String, password: String, repeatedPassword: String) -> Unit,
    startRegistration: () -> Unit,
    declineRegistrationData: () -> Unit,
    confirmRegistrationData: () -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = stringResource(Res.string.registration)) },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(imageVector = Icons.ArrowBack, contentDescription = stringResource(Res.string.input_password))
                }
            }
        )
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .verticalScroll(state = rememberScrollState())
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
        ) {
            RegistrationScreenContent(data = viewState,
                onNameChange = { name ->
                    handleChangeRegistrationData(name, viewState.password, viewState.repeatedPassword)
                },
                onPasswordChange = { password ->
                    handleChangeRegistrationData(viewState.name, password, viewState.repeatedPassword)
                },
                onRepeatedPasswordChange = { repeatedPassword ->
                    handleChangeRegistrationData(viewState.name, viewState.password, repeatedPassword)
                },
                onRegistrationClick = { startRegistration() })
        }
        if (viewState.isConfirmationRequested) {
            ConfirmDialog(
                title = stringResource(Res.string.confirm_registration_dialog_title),
                description = stringResource(Res.string.confirm_registration_dialog_description),
                onConfirmClick = { confirmRegistrationData() },
                onCancelClick = { declineRegistrationData() },
            )
        }
    }
}
