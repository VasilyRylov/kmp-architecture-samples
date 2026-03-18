package io.github.vasilyrylov.archsample.di

import android.content.Context
import io.github.vasilyrylov.archsample.common.data.preferences.AndroidPreferences
import io.github.vasilyrylov.archsample.common.data.preferences.IPreferences

fun getPreferences(appContext: Context): IPreferences {
    val preferencesName = "${appContext.packageName}_preferences"
    val sharedPreferences = appContext.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
    return AndroidPreferences(sharedPreferences)
}