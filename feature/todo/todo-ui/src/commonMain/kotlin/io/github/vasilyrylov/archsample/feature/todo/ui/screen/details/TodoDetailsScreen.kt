package io.github.vasilyrylov.archsample.feature.todo.ui.screen.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.vasilyrylov.archsample.common.domain.model.TodoItem
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.details.model.TodoDetailsScreenDialog
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.details.model.TodoDetailsViewState
import io.github.vasilyrylov.archsample.feature.todo.ui.element.dialog.EditTodoItemDialog
import io.github.vasilyrylov.archsample.common.ui.resources.Res
import io.github.vasilyrylov.archsample.common.ui.resources.arrow_back_24px
import io.github.vasilyrylov.archsample.common.ui.resources.back
import io.github.vasilyrylov.archsample.common.ui.resources.delete
import io.github.vasilyrylov.archsample.common.ui.resources.delete_24px
import io.github.vasilyrylov.archsample.common.ui.resources.edit
import io.github.vasilyrylov.archsample.common.ui.resources.edit_24px
import io.github.vasilyrylov.archsample.common.ui.resources.todo
import io.github.vasilyrylov.archsample.common.ui.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailsScreen(
    viewState: TodoDetailsViewState,
    onBackClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit,
    onConfirmEdit: (TodoItem) -> Unit,
    onCancelEdit: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(Res.string.todo)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(painter = painterResource(Res.drawable.arrow_back_24px), contentDescription = stringResource(Res.string.back))
                    }
                },
                actions = {
                    IconButton(onClick = onEditClick) {
                        Icon(painter = painterResource(Res.drawable.edit_24px), contentDescription = stringResource(Res.string.edit))
                    }
                    IconButton(onClick = onDeleteClick) {
                        Icon(painter = painterResource(Res.drawable.delete_24px), contentDescription = stringResource(Res.string.delete))
                    }
                }
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .consumeWindowInsets(paddingValues = paddingValues)
                .padding(paddingValues = paddingValues),
        ) {
            Box(modifier = Modifier.padding(all = 16.dp)) {
                Text(text = viewState.item.text)
            }
        }
    }

    when (viewState.dialog) {
        TodoDetailsScreenDialog.None -> Unit

        TodoDetailsScreenDialog.EditTodo -> EditTodoItemDialog(
            todoItem = viewState.item,
            onConfirmClick = onConfirmEdit,
            onCancelClick = onCancelEdit,
        )
    }
}

@Preview
@Composable
private fun TodoDetailsScreenPreview() {
    AppTheme {
        TodoDetailsScreen(
            viewState = TodoDetailsViewState(
                item = TodoItem(
                    text = "One",
                    completed = false
                )
            ),
            onBackClick = {},
            onDeleteClick = {},
            onEditClick = {},
            onConfirmEdit = {},
            onCancelEdit = {})

    }
}