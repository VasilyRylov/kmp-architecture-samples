package io.github.vasilyrylov.archsample.di

import io.github.vasilyrylov.archsample.common.data.preferences.IOsPreferences
import io.github.vasilyrylov.archsample.common.data.preferences.IPreferences
import platform.Foundation.NSUserDefaults

fun getPreferences(userDefaults: NSUserDefaults): IPreferences {
    return IOsPreferences(userDefaults)
}