package com.agiletech.dreamcaster.data.repository

import com.agiletech.dreamcaster.data.DreamsDataSource
import com.agiletech.dreamcaster.data.Result
import com.agiletech.dreamcaster.data.entities.Dream
import com.agiletech.dreamcaster.domain.repository.DreamsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DreamsRepositoryImpl(
    private val dreamsLocalDataSource: DreamsDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : DreamsRepository {

    override fun getDreamsStream(): Flow<Result<List<Dream>>> {
        return dreamsLocalDataSource.getDreamsStream()
    }

    override suspend fun getDreams(): Result<List<Dream>> {
        return try {
            dreamsLocalDataSource.getDreams()
        } catch (e: java.lang.Exception) {
            Result.Error(e)
        }
    }

    override suspend fun saveDream(dream: Dream) {
        coroutineScope {
            launch { dreamsLocalDataSource.saveDream(dream) }
        }
    }
}