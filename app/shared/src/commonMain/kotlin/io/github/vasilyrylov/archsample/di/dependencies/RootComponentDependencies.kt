package io.github.vasilyrylov.archsample.di.dependencies

import io.github.vasilyrylov.archsample.common.data.preferences.IPreferences
import io.github.vasilyrylov.archsample.data.database.ArchSampleDatabase
import io.github.vasilyrylov.archsample.feature.root.component.api.IRootComponentDependencies
import me.tatarka.inject.annotations.Inject

@Inject
class RootComponentDependencies(
    lazyPreferences: Lazy<IPreferences>,
    lazyDatabase: Lazy<ArchSampleDatabase>,
) : IRootComponentDependencies {
    override val preferences by lazyPreferences
    override val database by lazyDatabase
}
