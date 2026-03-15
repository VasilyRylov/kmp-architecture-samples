package io.github.vasilyrylov.archsample.feature.auth.ui.element

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.vasilyrylov.archsample.feature.auth.ui.data.RegistrationViewState
import io.github.vasilyrylov.archsample.feature.auth.ui.element.input.NameInputField
import io.github.vasilyrylov.archsample.feature.auth.ui.element.input.PasswordInputField
import io.github.vasilyrylov.archsample.common.ui.resources.Res
import io.github.vasilyrylov.archsample.common.ui.resources.password
import io.github.vasilyrylov.archsample.common.ui.resources.repeat_password
import io.github.vasilyrylov.archsample.common.ui.resources.sign_up
import org.jetbrains.compose.resources.stringResource

@Composable
fun RegistrationScreenContent(
    data: RegistrationViewState,
    onNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRepeatedPasswordChange: (String) -> Unit,
    onRegistrationClick: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = Modifier.height(height = 64.dp))
        NameInputField(
            modifier = Modifier.fillMaxWidth(),
            value = data.name,
            onValueChange = onNameChange
        )
        Spacer(modifier = Modifier.height(height = 16.dp))
        PasswordInputField(
            modifier = Modifier.fillMaxWidth(),
            value = data.password,
            onValueChange = onPasswordChange,
            placeholder = stringResource(Res.string.password)
        )
        Spacer(modifier = Modifier.height(height = 16.dp))
        PasswordInputField(
            modifier = Modifier.fillMaxWidth(),
            value = data.repeatedPassword,
            onValueChange = onRepeatedPasswordChange,
            placeholder = stringResource(Res.string.repeat_password)
        )
        if (!data.errorMessage.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(height = 16.dp))
            Text(
                text = data.errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(modifier = Modifier.height(height = 96.dp))

        if (data.isRegistrationInProgress) {
            CircularProgressIndicator()
        } else {
            SignUpButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onRegistrationClick,
            )
        }
    }
}

@Composable
private fun SignUpButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(text = stringResource(Res.string.sign_up))
    }
}
