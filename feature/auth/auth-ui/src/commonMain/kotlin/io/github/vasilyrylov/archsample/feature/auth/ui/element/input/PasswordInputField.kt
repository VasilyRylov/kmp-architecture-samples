package io.github.vasilyrylov.archsample.feature.auth.ui.element.input

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import io.github.vasilyrylov.archsample.common.ui.resources.Res
import io.github.vasilyrylov.archsample.common.ui.resources.input_password
import io.github.vasilyrylov.archsample.common.ui.resources.lock_24px
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun PasswordInputField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder) },
        leadingIcon = { Icon(painter = painterResource(Res.drawable.lock_24px), contentDescription = stringResource(Res.string.input_password)) },
        visualTransformation = PasswordVisualTransformation()
    )
}
