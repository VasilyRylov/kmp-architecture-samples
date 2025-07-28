package io.github.vasilyrylov.archsample.feature.todo.ui.screen.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.vasilyrylov.archsample.common.domain.model.TodoItem
import io.github.vasilyrylov.archsample.common.domain.model.TodoItemId
import io.github.vasilyrylov.archsample.common.ui.icons.Add
import io.github.vasilyrylov.archsample.common.ui.icons.Icons
import io.github.vasilyrylov.archsample.common.ui.icons.Logout
import io.github.vasilyrylov.archsample.feature.todo.ui.element.dialog.EditTodoItemDialog
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.list.model.TodoListViewState
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.list.model.TodoListScreenDialog
import io.github.vasilyrylov.archsample.resources.Res
import io.github.vasilyrylov.archsample.resources.add
import io.github.vasilyrylov.archsample.resources.logout
import io.github.vasilyrylov.archsample.resources.todo_list
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    viewState: TodoListViewState,
    onAddClick: () -> Unit,
    onCompletedChange: (TodoItemId) -> Unit,
    onTodoClick: (TodoItemId) -> Unit,
    onConfirmAddClick: (TodoItem) -> Unit,
    onCancelAddClick: () -> Unit,
    onLogoutClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(Res.string.todo_list))
                },
                actions = {
                    IconButton(onClick = onLogoutClick) {
                        Icon(imageVector = Icons.Logout, contentDescription = stringResource(Res.string.logout))
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = onAddClick) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Add, contentDescription = stringResource(Res.string.add))
                    Spacer(modifier = Modifier.width(width = 8.dp))
                    Text(text = stringResource(Res.string.add))
                }
            }
        },
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues = paddingValues)) {
            TodoListScreenContent(
                todoItems = viewState.todoItems,
                onCompletedChange = onCompletedChange,
                onTodoItemClick = onTodoClick
            )
        }
    }

    when (viewState.dialog) {
        TodoListScreenDialog.None -> Unit

        TodoListScreenDialog.AddTodo -> EditTodoItemDialog(
            todoItem = TodoItem(text = "", completed = false),
            onConfirmClick = onConfirmAddClick,
            onCancelClick = onCancelAddClick
        )
    }
}
