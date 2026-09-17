package io.github.vasilyrylov.archsample

import android.app.Application
import io.github.vasilyrylov.archsample.di.AndroidPlatformComponent
import io.github.vasilyrylov.archsample.di.AppComponent
import io.github.vasilyrylov.archsample.di.create

class ArchSampleApplication : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        val platformComponent = AndroidPlatformComponent::class.create(this.applicationContext)
        appComponent = AppComponent::class.create(platformComponent)
    }

    fun appComponent(): AppComponent = appComponent
}
