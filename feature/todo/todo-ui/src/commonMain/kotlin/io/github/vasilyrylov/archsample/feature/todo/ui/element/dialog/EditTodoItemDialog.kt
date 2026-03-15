package io.github.vasilyrylov.archsample.feature.todo.ui.element.dialog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.vasilyrylov.archsample.common.domain.model.TodoItem
import io.github.vasilyrylov.archsample.common.ui.resources.Res
import io.github.vasilyrylov.archsample.common.ui.resources.cancel
import io.github.vasilyrylov.archsample.common.ui.resources.save
import io.github.vasilyrylov.archsample.common.ui.resources.todo
import org.jetbrains.compose.resources.stringResource

@Composable
fun EditTodoItemDialog(
    todoItem: TodoItem,
    onCancelClick: () -> Unit,
    onConfirmClick: (TodoItem) -> Unit
) {
    var todoState by remember { mutableStateOf(todoItem) }

    AlertDialog(
        onDismissRequest = onCancelClick,
        title = { Text(text = stringResource(Res.string.todo)) },
        text = {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = todoState.text,
                onValueChange = { todoState = todoState.copy(text = it) },
            )
        },
        dismissButton = {
            TextButton(onClick = onCancelClick) {
                Text(text = stringResource(Res.string.cancel))
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirmClick(todoState) }) {
                Text(text = stringResource(Res.string.save))
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun AddTodoDialogPreview() {
    EditTodoItemDialog(TodoItem("test", false), {}, {})
}

