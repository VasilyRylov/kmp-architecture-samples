package io.github.vasilyrylov.archsample.di

import io.github.vasilyrylov.archsample.common.data.preferences.IPreferences
import io.github.vasilyrylov.archsample.data.database.ArchSampleDatabase
import io.github.vasilyrylov.archsample.di.dependencies.RootComponentDependencies
import io.github.vasilyrylov.archsample.feature.root.component.api.IRootComponentDependencies
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import me.tatarka.inject.annotations.Scope

@Scope
annotation class AppScope

@AppScope
@Component
abstract class AppComponent(
    @Component val platformComponent: PlatformComponent,
    // @Component val networkComponent: NetworkComponent,
) {

    abstract val rootComponentDependencies: IRootComponentDependencies

    @AppScope
    @Provides
    protected fun bind(it: RootComponentDependencies): IRootComponentDependencies = it

    companion object
}

abstract class PlatformComponent {
    abstract val preferences: IPreferences
    abstract val database: ArchSampleDatabase
}

//// Separate for instrumental tests mock
//abstract class NetworkComponent {
//    abstract val api: IApi
//}