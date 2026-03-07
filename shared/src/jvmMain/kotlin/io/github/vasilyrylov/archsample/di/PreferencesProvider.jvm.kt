package io.github.vasilyrylov.archsample.di

import io.github.vasilyrylov.archsample.common.data.preferences.IPreferences
import io.github.vasilyrylov.archsample.common.data.preferences.JVMPreferences

fun getPreferences(): IPreferences {
    return JVMPreferences()
}