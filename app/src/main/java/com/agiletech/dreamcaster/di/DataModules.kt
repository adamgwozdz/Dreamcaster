package com.agiletech.dreamcaster.di

import android.content.Context
import androidx.room.Room
import com.agiletech.dreamcaster.data.DreamsDataSource
import com.agiletech.dreamcaster.data.TagsDataSource
import com.agiletech.dreamcaster.data.local.DreamsLocalDataSource
import com.agiletech.dreamcaster.data.local.LocalDatabase
import com.agiletech.dreamcaster.data.local.TagsLocalDataSource
import com.agiletech.dreamcaster.data.repository.DreamsRepositoryImpl
import com.agiletech.dreamcaster.domain.repository.DreamsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDreamsRepository(
        dreamsLocalDataSource: DreamsDataSource,
        tagsLocalDataSource: TagsDataSource,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): DreamsRepository {
        return DreamsRepositoryImpl(dreamsLocalDataSource, tagsLocalDataSource, ioDispatcher)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideDreamsLocalDataSource(
        database: LocalDatabase,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): DreamsDataSource {
        return DreamsLocalDataSource(database.dreamsDao(), ioDispatcher)
    }

    @Singleton
    @Provides
    fun provideTagsLocalDataSource(
        database: LocalDatabase,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): TagsDataSource {
        return TagsLocalDataSource(database.tagsDao(), ioDispatcher)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): LocalDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            LocalDatabase::class.java,
            "Dreams.db"
        ).build()
    }
}