package io.github.vasilyrylov.archsample.common.ui.theme

import androidx.compose.runtime.Composable

// UIKit manages status bar appearance through the view controller and the system theme,
// so the in-app theme flag cannot influence it and is intentionally unused here.
@Composable
internal actual fun SystemAppearance(@Suppress("UNUSED_PARAMETER") useDarkTheme: Boolean) = Unit
