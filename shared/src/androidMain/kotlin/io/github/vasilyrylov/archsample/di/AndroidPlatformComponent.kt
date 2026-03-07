package io.github.vasilyrylov.archsample.di

import android.content.Context
import io.github.vasilyrylov.archsample.common.data.preferences.IPreferences
import io.github.vasilyrylov.archsample.data.database.ArchSampleDatabase
import io.github.vasilyrylov.archsample.data.database.getRoomDatabase
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@AppScope
@Component
abstract class AndroidPlatformComponent(private val applicationContext: Context) : PlatformComponent() {

    val providesApplicationContext: Context
        @AppScope @Provides get() = applicationContext

    @AppScope
    @Provides
    fun providesDatabase(applicationContext: Context): ArchSampleDatabase = getRoomDatabase(applicationContext)

    @AppScope
    @Provides
    fun providesPreferences(applicationContext: Context): IPreferences = getPreferences(applicationContext)
}
