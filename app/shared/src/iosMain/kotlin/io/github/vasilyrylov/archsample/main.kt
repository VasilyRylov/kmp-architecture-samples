package io.github.vasilyrylov.archsample

import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.ComponentContext
import io.github.vasilyrylov.archsample.di.AppComponent
import io.github.vasilyrylov.archsample.di.IOsPlatformComponent
import io.github.vasilyrylov.archsample.di.PlatformComponent
import io.github.vasilyrylov.archsample.feature.root.component.RootFlowComponent
import platform.Foundation.NSUserDefaults
import platform.UIKit.UIViewController

expect fun IOsPlatformComponent.Companion.createKmp(userDefaults: NSUserDefaults): IOsPlatformComponent

private val platformComponent: PlatformComponent = IOsPlatformComponent.createKmp(NSUserDefaults.standardUserDefaults)

expect fun AppComponent.Companion.createKmp(platformComponent: PlatformComponent): AppComponent

private val appComponent: AppComponent = AppComponent.createKmp(platformComponent)

fun MainViewController(context: ComponentContext): UIViewController {
    val root = RootFlowComponent(context, appComponent.rootComponentDependencies)

    return ComposeUIViewController {
        ComposeApp(root)
    }
}
