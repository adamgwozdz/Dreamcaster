package com.agiletech.dreamcaster.presentation.dreams.list

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import timber.log.Timber

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun DreamsListScreen(
    viewModel: DreamsListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Timber.d(uiState.items.toString())
    viewModel.createNewDream()
    Text(uiState.items.toString())
}