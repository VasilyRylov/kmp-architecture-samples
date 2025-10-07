package io.github.vasilyrylov.archsample.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import io.github.vasilyrylov.archsample.data.database.dao.TodoDao
import io.github.vasilyrylov.archsample.data.database.dao.UserDao
import io.github.vasilyrylov.archsample.data.database.entity.TodoEntity
import io.github.vasilyrylov.archsample.data.database.entity.UserEntity

@Database(
    version = 1,
    entities = [
        UserEntity::class,
        TodoEntity::class
    ],
    exportSchema = true
)
@ConstructedBy(ArchSampleDatabaseConstructor::class)
abstract class ArchSampleDatabase : RoomDatabase() {
    abstract fun getTodoDao(): TodoDao
    abstract fun getUserDao(): UserDao
}

@Suppress("KotlinNoActualForExpect")
expect object ArchSampleDatabaseConstructor : RoomDatabaseConstructor<ArchSampleDatabase> {
    override fun initialize(): ArchSampleDatabase
}