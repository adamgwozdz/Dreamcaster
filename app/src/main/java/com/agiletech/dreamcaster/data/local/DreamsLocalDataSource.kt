package com.agiletech.dreamcaster.data.local

import com.agiletech.dreamcaster.data.DreamsDataSource
import com.agiletech.dreamcaster.data.Result
import com.agiletech.dreamcaster.data.entities.Dream
import com.agiletech.dreamcaster.data.Result.Success
import com.agiletech.dreamcaster.data.Result.Error
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext


class DreamsLocalDataSource internal constructor(
    private val dreamsDao: DreamsDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : DreamsDataSource {

    override fun getDreamsStream(): Flow<Result<List<Dream>>> {
        return dreamsDao.observeDreams().map {
            Success(it)
        }
    }

    override suspend fun getDreams(): Result<List<Dream>> = withContext(ioDispatcher) {
        return@withContext try {
            Success(dreamsDao.getDreams())
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun saveDream(dream: Dream) = withContext(ioDispatcher) {
        dreamsDao.insertDream(dream)
    }
}