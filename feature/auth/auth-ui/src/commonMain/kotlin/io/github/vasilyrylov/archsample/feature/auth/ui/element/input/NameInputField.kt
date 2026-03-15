package io.github.vasilyrylov.archsample.feature.auth.ui.element.input

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import io.github.vasilyrylov.archsample.common.ui.resources.Res
import io.github.vasilyrylov.archsample.common.ui.resources.input_name
import io.github.vasilyrylov.archsample.common.ui.resources.name
import io.github.vasilyrylov.archsample.common.ui.resources.person_24px
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NameInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(fontSize = 16.sp),
        singleLine = true,
        placeholder = { Text(text = stringResource(Res.string.name)) },
        leadingIcon = { Icon(painter = painterResource(Res.drawable.person_24px), contentDescription = stringResource(Res.string.input_name)) }
    )
}
