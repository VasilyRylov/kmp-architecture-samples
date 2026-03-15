package io.github.vasilyrylov.archsample.feature.todo.ui.screen.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.vasilyrylov.archsample.common.domain.model.TodoItem
import io.github.vasilyrylov.archsample.common.domain.model.TodoItemId

@Composable
fun TodoListScreenContent(
    todoItems: List<TodoItem>,
    onTodoItemClick: (TodoItemId) -> Unit,
    onCompletedChange: (TodoItemId) -> Unit
) {
    LazyColumn {
        items(items = todoItems) { todoItem ->
            TodoItemContent(
                item = todoItem,
                onCompletedChange = onCompletedChange,
                onTodoItemClick = onTodoItemClick
            )
        }
    }
}

@Composable
fun TodoItemContent(
    item: TodoItem,
    onCompletedChange: (TodoItemId) -> Unit,
    onTodoItemClick: (TodoItemId) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .clickable { onTodoItemClick(item.id) }
            .padding(all = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = item.text)
        Checkbox(
            checked = item.completed,
            onCheckedChange = { onCompletedChange(item.id) }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TodoListScreenContentPreview() {
    TodoListScreenContent(todoItemsPreviewData, {}, {})
}


private val todoItemsPreviewData = listOf(
    TodoItem(
        text = "One",
        completed = false
    ),
    TodoItem(
        text = "Two",
        completed = false
    ),
    TodoItem(
        text = "Three",
        completed = true
    ),
)