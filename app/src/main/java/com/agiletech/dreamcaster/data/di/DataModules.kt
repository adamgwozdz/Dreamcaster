package com.agiletech.dreamcaster.data.di

import android.content.Context
import androidx.room.Room
import com.agiletech.dreamcaster.data.database.data_source.DreamsDataSource
import com.agiletech.dreamcaster.data.database.data_source.DreamsLocalDataSource
import com.agiletech.dreamcaster.data.database.LocalDatabase
import com.agiletech.dreamcaster.data.repository.DefaultDreamsRepository
import com.agiletech.dreamcaster.data.repository.DreamsRepository
import com.agiletech.dreamcaster.util.di.IoDispatcher
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
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): DreamsRepository {
        return DefaultDreamsRepository(dreamsLocalDataSource, ioDispatcher)
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
        return DreamsLocalDataSource(database.dreamsDao(), database.tagsDao(), ioDispatcher)
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