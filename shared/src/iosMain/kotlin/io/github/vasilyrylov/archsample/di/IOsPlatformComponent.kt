package io.github.vasilyrylov.archsample.di

import io.github.vasilyrylov.archsample.common.data.preferences.IPreferences
import io.github.vasilyrylov.archsample.data.database.ArchSampleDatabase
import io.github.vasilyrylov.archsample.data.database.getRoomDatabase
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import platform.Foundation.NSUserDefaults


@AppScope
@Component
abstract class IOsPlatformComponent(private val userDefaults: NSUserDefaults) : PlatformComponent() {
    @AppScope
    @Provides
    fun providesDatabase(): ArchSampleDatabase = getRoomDatabase()

    @AppScope
    @Provides
    fun providesPreferences(): IPreferences = getPreferences(userDefaults)

    companion object
}