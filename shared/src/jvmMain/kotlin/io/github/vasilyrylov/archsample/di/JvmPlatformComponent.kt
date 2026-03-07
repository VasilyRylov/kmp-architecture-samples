package io.github.vasilyrylov.archsample.di

import io.github.vasilyrylov.archsample.common.data.preferences.IPreferences
import io.github.vasilyrylov.archsample.data.database.ArchSampleDatabase
import io.github.vasilyrylov.archsample.data.database.getRoomDatabase
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@AppScope
@Component
abstract class JvmPlatformComponent : PlatformComponent() {

    @AppScope
    @Provides
    fun providesDatabase(): ArchSampleDatabase = getRoomDatabase()

    @AppScope
    @Provides
    fun providesPreferences(): IPreferences = getPreferences()
}