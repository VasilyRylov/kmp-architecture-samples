package io.github.vasilyrylov.archsample.feature.auth.ui.element.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import io.github.vasilyrylov.archsample.common.ui.resources.Res
import io.github.vasilyrylov.archsample.common.ui.resources.cancel
import io.github.vasilyrylov.archsample.common.ui.resources.ok
import org.jetbrains.compose.resources.stringResource

@Composable
fun ConfirmDialog(
    title: String,
    description: String,
    onCancelClick: () -> Unit,
    onConfirmClick: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onCancelClick,
        title = { Text(text = title) },
        text = { Text(text = description) },
        dismissButton = {
            TextButton(onClick = onCancelClick) {
                Text(text = stringResource(Res.string.cancel))
            }
        },
        confirmButton = {
            TextButton(onClick = onConfirmClick) {
                Text(text = stringResource(Res.string.ok))
            }
        },
    )
}
