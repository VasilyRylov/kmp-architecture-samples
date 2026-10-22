package io.github.vasilyrylov.archsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import io.github.vasilyrylov.archsample.feature.root.component.RootFlowComponent

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val rootFlowComponent = RootFlowComponent(
            componentContext = defaultComponentContext(),
            dependencies = (application as ArchSampleApplication).appComponent().rootComponentDependencies
        )

        setContent {
            ComposeApp(rootFlowComponent = rootFlowComponent)
        }
    }
}