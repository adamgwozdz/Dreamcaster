package com.agiletech.dreamcaster.presentation.dreams.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agiletech.dreamcaster.data.Result
import com.agiletech.dreamcaster.data.Result.Success
import com.agiletech.dreamcaster.data.database.entity.DreamEntity
import com.agiletech.dreamcaster.data.database.entity.TagEntity
import com.agiletech.dreamcaster.data.model.Dream
import com.agiletech.dreamcaster.data.repository.DreamsRepository
import com.agiletech.dreamcaster.util.Async
import com.agiletech.dreamcaster.util.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject

/**
 * UiState for the dream list screen.
 */
data class DreamsUiState(
    val items: List<Dream> = emptyList(),
    val isLoading: Boolean = false,
)

/**
 * ViewModel for the dream list screen.
 */
@HiltViewModel
class DreamsListViewModel @Inject constructor(
    private val dreamsRepository: DreamsRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val isLoading = MutableStateFlow(false)
    private val dreamsStream = dreamsRepository.getDreamsStream()
        .map {
            checkDreamsStream(it)
        }
        .onStart<Async<List<Dream>>> { emit(Async.Loading) }
        .catch { e ->
            Timber.d("Error while loading dreamsStream")
            if (e is IllegalArgumentException) throw e
            else emit(Async.Success(emptyList()))
        }

    private fun checkDreamsStream(x: Result<List<Dream>>): Async.Success<List<Dream>> {
        return if (x is Success) Async.Success(x.data) else Async.Success(emptyList())
    }

    val uiState: StateFlow<DreamsUiState> = combine(
        isLoading, dreamsStream
    ) { isLoading, dreamsStream ->
        when (dreamsStream) {
            is Async.Loading -> {
                DreamsUiState(isLoading = true)
            }
            is Async.Success -> {
                DreamsUiState(
                    items = dreamsStream.data,
                    isLoading = isLoading
                )
            }
        }
    }
        .stateIn(
            initialValue = DreamsUiState(isLoading = true),
            scope = viewModelScope,
            started = WhileUiSubscribed
        )

    fun createNewDream() = viewModelScope.launch {
        val id = UUID.randomUUID().toString()
        val dream = DreamEntity(id = id, title = "Test", content = "Description")
        val tag1 = TagEntity(dreamId = id, name = "tag1")
        val tag2 = TagEntity(dreamId = id, name = "tag2")
        val tags = listOf(tag1, tag2)
        dreamsRepository.saveDream(dream, tags)
    }
}